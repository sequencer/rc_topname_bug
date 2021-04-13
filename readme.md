# chipsallinace-playground

## Introduction
This is a template repository for those who want to develop RTL based on rocket-chip and even chipyard, being able to edit all sources from chisel environments without publish them to local ivy.
You can add your own submodule in `build.sc`.  
For more information please visit [mill documentation](https://com-lihaoyi.github.io/mill/page/configuring-mill.html)
after adding your own code, you can add your library to playground dependency, and re-index Intellij to add your own library.

## IDE support
For mill use
```bash
mill mill.bsp.BSP/install
```
then open by your favorite IDE, which supports [BSP](https://build-server-protocol.github.io/) 

## Pending PRs
Philosophy of this repository is **fast break and fast fix**.
This repository always tracks remote developing branches, it may need some patches to work, `make patch` will append below in sequence:
<!-- BEGIN-PATCH -->
barstools https://github.com/ucb-bar/barstools/pull/101  
chisel3 https://github.com/chipsalliance/chisel3/pull/1854  
rocket-chip https://github.com/chipsalliance/rocket-chip/pull/2809  
rocket-chip https://github.com/chipsalliance/rocket-chip/pull/2810  
dsptools https://github.com/ucb-bar/dsptools/pull/222  
firesim https://github.com/firesim/firesim/pull/747  
firesim https://github.com/firesim/firesim/pull/749  
firesim https://github.com/firesim/firesim/pull/750  
hwacha https://github.com/ucb-bar/hwacha/pull/30  
testchipip https://github.com/ucb-bar/testchipip/pull/126  
<!-- END-PATCH -->

## Why not Chipyard

1. Building Chisel and FIRRTL from sources, get rid of any version issue. You can view Chisel/FIRRTL source codes from IDEA.
1. No more make+sbt: Scala dependencies are managed by mill -> bsp -> IDEA, minimal IDEA indexing time.
1. flatten git submodule in dependency, get rid of submodule recursive update.

So generally, this repo is the fast and cleanest way to start your Chisel project codebase.

## Always keep update-to-date
You can use this template and start your own job by appending commits on it. GitHub Action will automatically bump all dependencies, you can merge or rebase `sequencer/master` to your branch.

## System Dependencies
Currently, only support **Arch Linux and Debian** sid, you can PR your own distributions, like Mac Homebrew, Fedora.  
**Notice Ubuntu and CentOS is unacceptable, since they have a stale package repository, not possible use official package manager to install these requirements, if you insist using them, please install requirements below by your self.**
* GNU Make
  - Arch Linux: make
  - Debian: make
* GNU Parallel
  - Arch Linux: parallel
  - Debian: parallel
* git
  - Arch Linux: git
  - Debian: git
* mill
  - Arch Linux: mill
## SanityTests
This package is the standalone tests to check is bumping correct or not, served as the unittest, this also can be a great example to illustrate usages.

**NOTICE: SanityTests also contains additional system dependencies:**
* python: execute `vlsi_mem_gen`
  - Arch Linux: python
  - Debian: python3
* clang: bootrom cross compiling and veriltor C++ -> binary compiling
  - Arch Linux: clang
  - Debian: clang
* llvm: gnu toolchain replacement 
  - Arch Linux: llvm
  - Debian: llvm
* lld: LLVM based linker
  - Arch Linux: lld
  - Debian: lld
* verilator -> Verilog -> C++ generation
  - Arch Linux: verilator
  - Debian: verilator
* cmake -> verilator emulator build system
  - Arch Linux: cmake
  - Debian: cmake
* ninja -> verilator emulator build system
  - Arch Linux: ninja
  - Debian: ninja-build
* dtc -> dependent by spike
  - Arch Linux: dtc
  - Debian: device-tree-compiler

## Bug
emulator compiling crash log:
```
X sanitytests.rocketchip.VerilatorTest.build TestHarness emulator 86555ms
  os.SubprocessException: CommandResult 1
  [1/3] Generating CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__1.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__2.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__3.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__4.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__5.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__6.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__7.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__8.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__9.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__10.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__11.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__12.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__13.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__14.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__15.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__16.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__17.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__18.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__19.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness___024unit.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__Slow.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__1__Slow.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__2__Slow.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__3__Slow.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__4__Slow.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__5__Slow.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness___024unit__Slow.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__Dpi.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__Syms.cpp, CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness.cmake
  [2/3] Generating CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness_copy.cmake
  [1/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__19.cpp.o
  [2/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__16.cpp.o
  [3/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__1.cpp.o
  [4/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__2__Slow.cpp.o
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__2__Slow.cpp:606:22: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
              = (1U & (~ ((0U == vlTOPp->TestHarness__DOT__ldut__DOT__tile_prci_domain__DOT__tile_reset_domain_tile__DOT__dcache__DOT__tlb_pmp__DOT___res_hit_msbsEqual_T_90)
                      ~^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                       !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__2__Slow.cpp:619:22: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
                    : (~ (((0U == vlTOPp->TestHarness__DOT__ldut__DOT__tile_prci_domain__DOT__tile_reset_domain_tile__DOT__dcache__DOT__tlb_pmp__DOT___res_hit_msbsEqual_T_6)
                       ^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                       !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__2__Slow.cpp:637:22: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
                    : (~ (((0U == vlTOPp->TestHarness__DOT__ldut__DOT__tile_prci_domain__DOT__tile_reset_domain_tile__DOT__dcache__DOT__tlb_pmp__DOT___res_hit_msbsEqual_T_20)
                       ^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                       !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__2__Slow.cpp:655:22: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
                    : (~ (((0U == vlTOPp->TestHarness__DOT__ldut__DOT__tile_prci_domain__DOT__tile_reset_domain_tile__DOT__dcache__DOT__tlb_pmp__DOT___res_hit_msbsEqual_T_34)
                       ^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                       !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__2__Slow.cpp:673:22: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
                    : (~ (((0U == vlTOPp->TestHarness__DOT__ldut__DOT__tile_prci_domain__DOT__tile_reset_domain_tile__DOT__dcache__DOT__tlb_pmp__DOT___res_hit_msbsEqual_T_48)
                       ^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                       !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__2__Slow.cpp:691:22: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
                    : (~ (((0U == vlTOPp->TestHarness__DOT__ldut__DOT__tile_prci_domain__DOT__tile_reset_domain_tile__DOT__dcache__DOT__tlb_pmp__DOT___res_hit_msbsEqual_T_62)
                       ^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                       !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__2__Slow.cpp:709:22: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
                    : (~ (((0U == vlTOPp->TestHarness__DOT__ldut__DOT__tile_prci_domain__DOT__tile_reset_domain_tile__DOT__dcache__DOT__tlb_pmp__DOT___res_hit_msbsEqual_T_76)
                       ^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                       !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__2__Slow.cpp:727:22: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
                    : (~ (((0U == vlTOPp->TestHarness__DOT__ldut__DOT__tile_prci_domain__DOT__tile_reset_domain_tile__DOT__dcache__DOT__tlb_pmp__DOT___res_hit_msbsEqual_T_90)
                       ^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                       !
  8 warnings generated.
  [5/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__18.cpp.o
  [6/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__14.cpp.o
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__14.cpp:7390:13: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
          = ((~ ((3U == (IData)(vlTOPp->TestHarness__DOT__ldut__DOT__subsystem_cbus__DOT__atomics__DOT__cam_s_0_state))
             ~^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
              !
  1 warning generated.
  [7/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__3.cpp.o
  [8/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness___024unit__Slow.cpp.o
  [9/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__3__Slow.cpp.o
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__3__Slow.cpp:8917:13: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
          = ((~ ((3U == (IData)(vlTOPp->TestHarness__DOT__ldut__DOT__subsystem_cbus__DOT__atomics__DOT__cam_s_0_state))
             ~^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
              !
  1 warning generated.
  [10/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__Slow.cpp.o
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__Slow.cpp:6626:15: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
             & (~ ((3U < (IData)(vlTOPp->TestHarness__DOT__ldut__DOT__tile_prci_domain__DOT__tile_reset_domain_tile__DOT__dcache__DOT__lrscCount))
               ~^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__Slow.cpp:7280:53: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
                                                  & ((~
                                                     ~^
                                                      !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__Slow.cpp:7271:50: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
                                     >> 3U)))) ? ((~
                                                  ~^
                                                   !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__Slow.cpp:8950:15: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
             & (~ ((0ULL == (0xc000000000ULL & vlTOPp->TestHarness__DOT__ldut__DOT__tile_prci_domain__DOT__tile_reset_domain_tile__DOT__dcache__DOT__s1_tlb_req_vaddr))
               ~^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                !
  4 warnings generated.
  [11/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__13.cpp.o
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__13.cpp:3708:15: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
             & (~ ((0ULL == (0xc000000000ULL & vlTOPp->TestHarness__DOT__ldut__DOT__tile_prci_domain__DOT__tile_reset_domain_tile__DOT__dcache__DOT__s1_tlb_req_vaddr))
               ~^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__13.cpp:3982:15: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
             & (~ ((3U < (IData)(vlTOPp->TestHarness__DOT__ldut__DOT__tile_prci_domain__DOT__tile_reset_domain_tile__DOT__dcache__DOT__lrscCount))
               ~^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                !
  2 warnings generated.
  [12/37] Building CXX object CMakeFiles/emulator.dir/home/sequencer/chipsalliance-playground/sanitytests/rocketchip/resources/csrc/SimJTAG.cc.o
  [13/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__Dpi.cpp.o
  [14/37] Building CXX object CMakeFiles/emulator.dir/home/sequencer/chipsalliance-playground/sanitytests/rocketchip/resources/csrc/remote_bitbang.cc.o
  [15/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__Syms.cpp.o
  [16/37] Building CXX object CMakeFiles/emulator.dir/home/sequencer/chipsalliance-playground/sanitytests/rocketchip/resources/csrc/SimDTM.cc.o
  [17/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness___024unit.cpp.o
  [18/37] Building CXX object CMakeFiles/emulator.dir/home/sequencer/chipsalliance-playground/sanitytests/rocketchip/resources/csrc/emulator.cc.o
  FAILED: CMakeFiles/emulator.dir/home/sequencer/chipsalliance-playground/sanitytests/rocketchip/resources/csrc/emulator.cc.o
  clang++ -DVM_COVERAGE=0 -DVM_SC=0 -DVM_TRACE=0 -DVM_TRACE_FST=0 -DVM_TRACE_VCD=0 -I/home/sequencer/chipsalliance-playground/out/sanitytests/rocketchip/libraryResources/dest/usr/include -ICMakeFiles/emulator.dir/VTestHarness.dir -I/usr/share/verilator/include -I/usr/share/verilator/include/vltstd -DVERILATOR -DTEST_HARNESS=VTestHarness -std=c++11 -include /home/sequencer/chipsalliance-playground/sanitytests/rocketchip/resources/csrc/verilator.h -include /home/sequencer/chipsalliance-playground/out/VerilatorTest/TestHarness.plusArgs -include VTestHarness.h -MD -MT CMakeFiles/emulator.dir/home/sequencer/chipsalliance-playground/sanitytests/rocketchip/resources/csrc/emulator.cc.o -MF CMakeFiles/emulator.dir/home/sequencer/chipsalliance-playground/sanitytests/rocketchip/resources/csrc/emulator.cc.o.d -o CMakeFiles/emulator.dir/home/sequencer/chipsalliance-playground/sanitytests/rocketchip/resources/csrc/emulator.cc.o -c /home/sequencer/chipsalliance-playground/sanitytests/rocketchip/resources/csrc/emulator.cc
  /home/sequencer/chipsalliance-playground/sanitytests/rocketchip/resources/csrc/emulator.cc:284:61: error: no member named 'io_success' in 'VTestHarness'
      if (done_reset && (dtm->done() || jtag->done() || tile->io_success))
                                                        ~~~~  ^
  1 error generated.
  [19/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__12.cpp.o
  [20/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__17.cpp.o
  [21/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__15.cpp.o
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__15.cpp:524:15: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
             & (~ ((0ULL == (0xc000000000ULL & vlTOPp->TestHarness__DOT__ldut__DOT__tile_prci_domain__DOT__tile_reset_domain_tile__DOT__frontend__DOT__s1_pc))
               ~^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__15.cpp:945:53: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
                                                  & ((~
                                                     ~^
                                                      !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__15.cpp:936:50: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
                                     >> 3U)))) ? ((~
                                                  ~^
                                                   !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__15.cpp:1019:22: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
              = (1U & (~ ((0U == vlTOPp->TestHarness__DOT__ldut__DOT__tile_prci_domain__DOT__tile_reset_domain_tile__DOT__dcache__DOT__tlb_pmp__DOT___res_hit_msbsEqual_T_90)
                      ~^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                       !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__15.cpp:1032:22: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
                    : (~ (((0U == vlTOPp->TestHarness__DOT__ldut__DOT__tile_prci_domain__DOT__tile_reset_domain_tile__DOT__dcache__DOT__tlb_pmp__DOT___res_hit_msbsEqual_T_6)
                       ^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                       !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__15.cpp:1050:22: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
                    : (~ (((0U == vlTOPp->TestHarness__DOT__ldut__DOT__tile_prci_domain__DOT__tile_reset_domain_tile__DOT__dcache__DOT__tlb_pmp__DOT___res_hit_msbsEqual_T_20)
                       ^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                       !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__15.cpp:1068:22: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
                    : (~ (((0U == vlTOPp->TestHarness__DOT__ldut__DOT__tile_prci_domain__DOT__tile_reset_domain_tile__DOT__dcache__DOT__tlb_pmp__DOT___res_hit_msbsEqual_T_34)
                       ^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                       !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__15.cpp:1086:22: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
                    : (~ (((0U == vlTOPp->TestHarness__DOT__ldut__DOT__tile_prci_domain__DOT__tile_reset_domain_tile__DOT__dcache__DOT__tlb_pmp__DOT___res_hit_msbsEqual_T_48)
                       ^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                       !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__15.cpp:1104:22: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
                    : (~ (((0U == vlTOPp->TestHarness__DOT__ldut__DOT__tile_prci_domain__DOT__tile_reset_domain_tile__DOT__dcache__DOT__tlb_pmp__DOT___res_hit_msbsEqual_T_62)
                       ^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                       !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__15.cpp:1122:22: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
                    : (~ (((0U == vlTOPp->TestHarness__DOT__ldut__DOT__tile_prci_domain__DOT__tile_reset_domain_tile__DOT__dcache__DOT__tlb_pmp__DOT___res_hit_msbsEqual_T_76)
                       ^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                       !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__15.cpp:1140:22: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
                    : (~ (((0U == vlTOPp->TestHarness__DOT__ldut__DOT__tile_prci_domain__DOT__tile_reset_domain_tile__DOT__dcache__DOT__tlb_pmp__DOT___res_hit_msbsEqual_T_90)
                       ^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                       !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__15.cpp:4309:25: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
                       & (~ (((0x340U <= (0xfffU & (vlTOPp->TestHarness__DOT__ldut__DOT__tile_prci_domain__DOT__tile_reset_domain_tile__DOT__core__DOT__ibuf_nBufValid_io_inst_0_bits_raw
                         ~^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                          !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__15.cpp:11282:54: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
                                                  & (((~
                                                      ~^
                                                       !
  13 warnings generated.
  [22/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__2.cpp.o
  [23/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__7.cpp.o
  [24/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__10.cpp.o
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__10.cpp:6533:26: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
                  = (1U & (~ ((1U == (IData)(vlTOPp->TestHarness__DOT__ldut__DOT__subsystem_sbus__DOT__coupler_to_port_named_mmio_port_axi4__DOT__tl2axi4__DOT__counter))
                          ~^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                           !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__10.cpp:6867:26: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
                  = (1U & (~ ((1U == (IData)(vlTOPp->TestHarness__DOT__ldut__DOT__subsystem_mbus__DOT__coupler_to_memory_controller_port_named_axi4__DOT__tl2axi4__DOT__counter))
                          ~^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                           !
  2 warnings generated.
  [25/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__1__Slow.cpp.o
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__1__Slow.cpp:1699:15: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
             & (~ ((0ULL == (0xc000000000ULL & vlTOPp->TestHarness__DOT__ldut__DOT__tile_prci_domain__DOT__tile_reset_domain_tile__DOT__frontend__DOT__s1_pc))
               ~^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                !
  CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__1__Slow.cpp:2049:54: warning: bitwise negation of a boolean expression; did you mean logical negation? [-Wbool-operation]
                                                  & (((~
                                                      ~^
                                                       !
  2 warnings generated.
  [26/37] Building CXX object CMakeFiles/emulator.dir/usr/share/verilator/include/verilated_dpi.cpp.o
  [27/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__6.cpp.o
  [28/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__9.cpp.o
  [29/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__5.cpp.o
  [30/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__8.cpp.o
  [31/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness.cpp.o
  [32/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__4__Slow.cpp.o
  [33/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__4.cpp.o
  [34/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__5__Slow.cpp.o
  [35/37] Building CXX object CMakeFiles/emulator.dir/CMakeFiles/emulator.dir/VTestHarness.dir/VTestHarness__11.cpp.o
  [36/37] Building CXX object CMakeFiles/emulator.dir/usr/share/verilator/include/verilated.cpp.o
  ninja: build stopped: subcommand failed.
    os.proc.call(ProcessOps.scala:85)
    sanitytests.rocketchip.TestHarness.emulator$lzycompute(TestHarness.scala:118)
    sanitytests.rocketchip.TestHarness.emulator(TestHarness.scala:21)
    sanitytests.rocketchip.VerilatorTest$.$anonfun$tests$2(VerilatorTest.scala:20)
1 targets failed
sanitytests.rocketchip.test sanitytests.rocketchip.VerilatorTest sanitytests.rocketchip.VerilatorTest.build TestHarness emulator
make: *** [Makefile:25: test] Error 1
```

What happend?
In `out/VerilatorTest/TestHarness.fir`:
Top IO name is wrong:
```
  module TestHarness :
    input clock : Clock
    input reset : UInt<1>
    output in_0_b_bits_io : {success : UInt<1>}
```

