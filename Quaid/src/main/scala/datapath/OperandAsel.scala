package datapath
import chisel3._
class OperandAsel extends Module{
	val io = IO(new Bundle{
	val LUI = Input(UInt(1.W))
	val JALR = Input(UInt(1.W))
	val JAL = Input(UInt(1.W))
	val Oper_A_sel = Output(UInt(2.W)) 
})
	io.Oper_A_sel := "b00".U
	when(io.LUI === 1.U & io.JAL === 0.U & io.JALR === 0.U){
	io.Oper_A_sel := "b11".U
}
	.elsewhen( io.JAL === 1.U | io.JALR === 1.U){
	io.Oper_A_sel := "b10".U
} 
}
