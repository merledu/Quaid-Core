package datapath
import chisel3._
class Top extends Module{
 	val io = IO(new Bundle{
	val Instruction = Output(UInt(32.W))
	val reg_out = Output(SInt(32.W))
})
	io.reg_out := 0.S
	
	val control = Module(new Control())
	val ALU = Module(new ALU())
	val ALUcontrol = Module(new ALUcontrol())
	val Imm_Gen = Module(new ImmediateGeneration())
	val Register = Module(new Register())
	val JALR = Module(new JALR())
	val PC1 = Module(new PC())
	val Data_Mem = Module(new DataMemory)
	ALU.io.A := 0.S
	ALU.io.B := 0.S
	Register.io.writedata := 0.S
	val InstructionMemory = Module(new InstructionMemory())
	io.Instruction := InstructionMemory.io.readData
	InstructionMemory.io.writeAddress := PC1.io.pc(11,2)
	control.io.opcode := io.Instruction(6,0)
	ALUcontrol.io.Func7 := io.Instruction(30)
	ALUcontrol.io.Func3 := io.Instruction(14,12)
	ALUcontrol.io.ALUop := control.io.ALUop
	Imm_Gen.io.Instruction := io.Instruction 
	ALU.io.ALUcontrol := ALUcontrol.io.Ctrl
	Register.io.rs1_sel := io.Instruction(19,15)
	Register.io.rs2_sel := io.Instruction(24,20)
	Register.io.rd_sel := io.Instruction(11,7)
	Imm_Gen.io.PC := PC1.io.pc
	PC1.io.input := PC1.io.pc4
	Register.io.regwrite := control.io.Regwrite
	JALR.io.rs1 := Register.io.rs1
	when(control.io.Extend_sel === 1.U){
	 when(control.io.Oper_A_sel === 3.U){
	  when(control.io.Oper_B_sel === 1.U){
	   ALU.io.A := Register.io.rs1
	   ALU.io.B := Imm_Gen.io.U_Immediate
	   when(control.io.MemtoReg === 1.U){
	    Register.io.writedata := ALU.io.output
}
}
}
}
	
	when(control.io.Oper_B_sel === 1.U){
	when(control.io.Extend_sel === "b00".U){
	ALU.io.B := Imm_Gen.io.I_Immediate
}
	.elsewhen(control.io.Extend_sel === "b10".U){
	ALU.io.B := Imm_Gen.io.S_Immediate
	ALU.io.A := Register.io.rs1
}
	.elsewhen(control.io.Extend_sel === "b01".U){
	ALU.io.B := Imm_Gen.io.U_Immediate
}
}
	.otherwise{
	ALU.io.B := Register.io.rs2
}
	JALR.io.rs2 := Imm_Gen.io.I_Immediate
	when(control.io.Oper_A_sel === "b00".U || control.io.Oper_A_sel === "b11".U){
	ALU.io.A := Register.io.rs1
}
	.elsewhen(control.io.Oper_A_sel === "b10".U){
	ALU.io.A := PC1.io.pc4.asSInt
}

	when(control.io.Next_PC_sel === "b00".U){
	PC1.io.input := PC1.io.pc4
}
	.elsewhen(control.io.Next_PC_sel === "b01".U){
	when(ALU.io.Branch === control.io.Branch1){
	val imm = Imm_Gen.io.SB_Immediate.asUInt
	PC1.io.input := imm
}
}
	.elsewhen(control.io.Next_PC_sel === "b10".U){
	PC1.io.input := Imm_Gen.io.UJ_Immediate.asUInt
}
	.elsewhen(control.io.Next_PC_sel === "b11".U){
	val imm = JALR.io.JALR.asUInt
	PC1.io.input := imm
}
	Data_Mem.io.Address := ALU.io.output(9,2)
	Data_Mem.io.Read_Data := Register.io.rs2
	Data_Mem.io.Mem_Read := control.io.MemRead
	Data_Mem.io.Mem_Write := control.io.Memwrite
	when(control.io.MemtoReg === 1.U){
	 Register.io.writedata := Data_Mem.io.Data
}
	.elsewhen(control.io.MemtoReg === 0.U){
	  Register.io.writedata := ALU.io.output
}
	
}
