// See LICENSE.txt for license details.
package datapath

import chisel3.iotesters.{Driver, TesterOptionsManager}
import utils.TutorialRunner

object Launcher {
  val examples = Map(
      "InstructionTypeDecode" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new InstructionTypeDecode(), manager) {
          (c) => new InstructionTypeDecodeTests(c)
        }
      },
	"OperandAsel" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new OperandAsel(), manager) {
          (c) => new OperandAselTests(c)
        }
      },
	"ControlDecoder" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new ControlDecoder(), manager) {
          (c) => new ControlDecoderTests(c)
        }
      },
	"Control" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Control(), manager) {
          (c) => new controlTests(c)
        }
      },
	"ALUcontrol" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new ALUcontrol(), manager) {
          (c) => new ALUcontrolTests(c)
        }
      },
	"ALU" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new ALU(), manager) {
          (c) => new ALUTests(c)
        }
      },
	"ImmediateGeneration" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new ImmediateGeneration(), manager) {
          (c) => new ImmediateGenerationTests(c)
        }
      },
	"Register" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Register(), manager) {
          (c) => new RegisterTests(c)
        }
      },
	"Top" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Top(), manager) {
          (c) => new TopTests(c)
        }
      },
	"JALR" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new JALR(), manager) {
          (c) => new JALRTests(c)
        }
      },
	"PC" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new PC(), manager) {
          (c) => new PCTests(c)
        }
      },
	"InstructionMemory" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new InstructionMemory(), manager) {
          (c) => new InstructionMemoryTests(c)
        }
      },
	"DataMemory" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new DataMemory(), manager) {
          (c) => new DataMemoryTests(c)
        }
      }
  )
  def main(args: Array[String]): Unit = {
    TutorialRunner("examples", examples, args)
  }
}

