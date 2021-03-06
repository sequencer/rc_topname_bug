package playground

import chisel3.stage.{ChiselGeneratorAnnotation, NoRunFirrtlCompilerAnnotation}
import firrtl.AnnotationSeq
import firrtl.options.TargetDirAnnotation
import midas.stage.phases.ConfigParametersAnnotation
import midas.{WithoutTLMonitors => _}

object Playground extends App {

  /** [[midas.stage.GoldenGateStage]] as main stage:
    *
    * [[midas.stage.phases.CreateParametersInstancePhase]] -> [[midas.stage.phases.ConfigParametersAnnotation]]:
    *   RocketChip already have [[freechips.rocketchip.stage.ConfigsAnnotation]] and
    *   [[freechips.rocketchip.stage.TopModuleAnnotation]] to handle this.
    * [[midas.stage.GoldenGateCompilerPhase]] will replace original [[firrtl.stage.FirrtlStage]], consume:
    * - CHIRRTL -> [[firrtl.stage.FirrtlCircuitAnnotation]]
    * - insert [[firrtl.passes.memlib.InferReadWriteAnnotation]]
    * - loweredTarget -> [[midas.passes.DedupModules]] ++
    *     This pass is a MIDAS specific dedup pass, which was copy-and-paste from FIRRTL 1.3,
    *     don't know why firesim did this.
    *                    [[firrtl.stage.Forms.LowForm]] ->
    * - simulator -> [[firrtl.stage.Forms.LowForm]] ++
    *                [[firrtl.passes.memlib.InferReadWrite]] ++
    *     If any product term of the enable signal of the read port is the complement of any product term of the enable
    *     signal of the write port, then the readwrite port is inferred.
    *     don't know why firesim need this.
    *                [[midas.passes.MidasTransforms]] ->
    *   - [[midas.passes.CoerceAsyncToSyncReset]]:
    *     Replaces all AsyncResets and Reset types with Bool, this treat all asynchronous reset as synchronous.
    *   - [[midas.passes.EnsureNoTargetIO]]:
    *     Ensures that there are no dangling IO on the target.
    *     All I/O coming off the DUT must be bound to an Bridge BlackBox.
    *   - [[midas.passes.BridgeExtraction]]:
    *     consume [[midas.widgets.BridgeAnnotation]], which is added in [[firesim.bridges.BlockDevBridge]]
    *     generate [[midas.passes.BridgeExtraction.BridgeInstance]]
    *   - [[midas.passes.AutoCounterTransform]]:
    *     consume [[midas.targetutils.AutoCounterFirrtlAnnotation]] generated by
    *     [[midas.passes.FireSimPropertyLibrary]] which is an implementation of
    *     [[freechips.rocketchip.util.property.BasePropertyLibrary]].
    *     consume parameters [[midas.EnableAutoCounter]] [[midas.AutoCounterUsePrintfImpl]]
    *     generate [[midas.widgets.BridgeIOAnnotation]],
    *              [[midas.targetutils.TriggerSinkAnnotation]],
    *              [[midas.passes.fame.FAMEChannelConnectionAnnotation]]
    *     remove [[midas.passes.BridgeTopWiringOutputAnnotation]]
    *            [[midas.targetutils.AutoCounterCoverModuleFirrtlAnnotation]]
    *            [[midas.targetutils.AutoCounterFirrtlAnnotation]]
    *   - [[midas.passes.AssertPass]]:
    *     match all stop(since chisel currently don't generate assert)
    *   - [[midas.passes.PrintSynthesis]]:
    *     printf(midas.targetutils.SynthesizePrintf("x%d p%d 0x%x\n", rf_waddr, rf_waddr, rf_wdata)) will construct a
    *     firesim print.
    *     consumes [[midas.targetutils.SynthPrintfAnnotation]]
    *   - [[midas.passes.TriggerWiring]]
    *     Fore Golden Gate, consumes [[midas.targetutils.TriggerSourceAnnotation]] and
    *     [[midas.targetutils.TriggerSinkAnnotation]]/s
    *   - [[midas.passes.ChannelClockInfoAnalysis]]
    *     generate [[midas.passes.ChannelClockInfoAnnotation]] to analysis clock.
    *   - [[midas.passes.UpdateBridgeClockInfo]]
    *   - [[midas.passes.fame.WrapTop]]
    *     wrap dut into top, annotate host clock with [[midas.passes.fame.FAMEHostClock]] and reset with
    *     [[midas.passes.fame.FAMEHostReset]]
    *   - [[midas.passes.fame.LabelMultiThreadedInstances]]
    *     ???
    *   - [[midas.passes.fame.LabelSRAMModels]] if [[midas.GenerateMultiCycleRamModels]] is configured
    *     convert all RAMs to model and generate [[midas.targetutils.FirrtlFAMEModelAnnotation]]
    *   - [[midas.passes.fame.ExtractModel]]
    *     consume [[midas.targetutils.FirrtlFAMEModelAnnotation]], do ???
    *   - [[midas.passes.fame.FAMEDefaults]]
    *   - [[midas.passes.fame.FindDefaultClocks]]
    *   - [[midas.passes.fame.ChannelExcision]]
    *   - [[midas.passes.fame.InferModelPorts]]
    *   - [[midas.passes.fame.FAMETransform]]
    *   - [[midas.passes.DefineAbstractClockGate]]
    *   - [[midas.passes.fame.MultiThreadFAME5Models]]
    *   - [[midas.passes.fame.EmitAndWrapRAMModels]]
    *   - [[midas.passes.SimulationMapping]]
    *   - [[midas.passes.xilinx.HostSpecialization]]
    *       [[midas.passes.xilinx.DefineBUFGCE]]
    *       [[midas.passes.xilinx.ReplaceAbstractClockGates]]
    *       [[midas.passes.xilinx.FPGAFriendlyMems]]
    *   hostLoweringCompiler -> [[firrtl.VerilogEmitter]]
    *                           [[midas.HostTransforms]]
    *   This seems to a workaround to dependency API.
    *   FAME seems like a FIRRTL based FPGA-shell.
    */

  Seq(
    new chisel3.stage.ChiselStage,
    new midas.stage.GoldenGateStage,
    new midas.stage.RuntimeConfigGeneratorStage
  ).foldLeft(
    AnnotationSeq(
      Seq(
        ChiselGeneratorAnnotation(() => new firesim.midasexamples.GCD()(new firesim.midasexamples.NoConfig)),
        ConfigParametersAnnotation((new midas.HostDebugFeatures).alter(new firesim.midasexamples.DefaultF1Config)),
        TargetDirAnnotation("/tmp/firesim"),
        NoRunFirrtlCompilerAnnotation
      )
    )
  ) { case (anno, stage) => stage.transform(anno) }
}
