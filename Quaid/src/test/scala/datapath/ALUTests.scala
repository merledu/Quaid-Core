package datapath
import chisel3.iotesters.PeekPokeTester
class ALUTests(c:ALU) extends PeekPokeTester(c){
	poke(c.io.ALUcontrol, 13)
	poke(c.io.A, -2)
	poke(c.io.B, 4)
	step(1)
	expect(c.io.output, 123)
	expect(c.io.Branch, 0)
}

