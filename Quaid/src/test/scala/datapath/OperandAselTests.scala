package datapath
import chisel3.iotesters.PeekPokeTester
class OperandAselTests(c:OperandAsel) extends PeekPokeTester(c){
	poke(c.io.LUI, 1)
	poke(c.io.JAL, 0)
	poke(c.io.JALR, 0)
	step(1)
	expect(c.io.Oper_A_sel, 3)
}
