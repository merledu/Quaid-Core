package datapath
import chisel3._
import chisel3.util.experimental.loadMemoryFromFile
class InstructionMemory extends Module{
	val io = IO(new Bundle{
	val writeAddress = Input(UInt(10.W))
	val readData = Output(UInt(32.W))
})
	val mem = Mem(1024,UInt(32.W))
	io.readData := mem(io.writeAddress)
	loadMemoryFromFile(mem,"/Desktop/practice/data.txt")
}
