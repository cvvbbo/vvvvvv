# Android onclick fragment  into  new pager
问问神奇海螺啊

sorry for my english~
i study android some  time 。i find a switch between interfaces

it can be used in the news  client, the details interface, which used to be the jump from the instantiated object, and the start of the startActivity () method。

i use two handle do this。one handle do page auto jump。author handle do jump to details interface


==========================
D:\xmr-stak-dep\xmr-stak-master>"d:\Program Files (x86)\Microsoft Visual Studio\2017\Community\Common7\Tools\VsMSBuildCmd.bat"
**********************************************************************
** Visual Studio 2017 MSBuild Command Prompt
** Copyright (c) 2017 Microsoft Corporation
**********************************************************************
C:\Users\xiong\source>set CMAKE_PREFIX_PATH=C:\xmr-stak-dep\hwloc;C:\xmr-stak-dep\libmicrohttpd;C:\xmr-stak-dep\openssl

C:\Users\xiong\source>cmake -G "Visual Studio 15 2017 Win64" -T v141,host=x64 ..
CMake Error: The source directory "C:/Users/xiong" does not appear to contain CMakeLists.txt.
Specify --help for usage, or press the help button on the CMake GUI.

C:\Users\xiong\source>cd build

C:\Users\xiong\source\build>cmake -G "Visual Studio 15 2017 Win64" -T v141,host=x64 ..
CMake Error at CMakeLists.txt:1 (project):
  Failed to run MSBuild command:

    MSBuild.exe

  to get the value of VCTargetsPath:

    用于 .NET Framework 的 Microsoft (R) 生成引擎版本 15.5.180.51428
    版权所有(C) Microsoft Corporation。保留所有权利。

    生成启动时间为 2018/1/12 22:25:30。
    节点 1 上的项目“C:\Users\xiong\source\build\CMakeFiles\3.9.0-rc3\VCTargetsPath.vcxproj”(默认目标)。
    d:\Program Files (x86)\Microsoft Visual Studio\2017\Community\Common7\IDE\VC\VCTargets\Microsoft.Cpp.Current.targets(108,3): error MSB4019: 未找到导入的项目“d:\Program Files (x86)\Microsoft Visual Studio\2017\Community\Common7\IDE\VC\VCTargets\Platforms\x64\Platform.targets”。请确认 <Import> 声明中的路径正确，且磁盘上存在该文件。 [C:\Users\xiong\source\build\CMakeFiles\3.9.0-rc3\VCTargetsPath.vcxproj]
    已完成生成项目“C:\Users\xiong\source\build\CMakeFiles\3.9.0-rc3\VCTargetsPath.vcxproj”(默认目标)的操作 - 失败。

    生成失败。

    “C:\Users\xiong\source\build\CMakeFiles\3.9.0-rc3\VCTargetsPath.vcxproj”(默认目标) (1) ->
      d:\Program Files (x86)\Microsoft Visual Studio\2017\Community\Common7\IDE\VC\VCTargets\Microsoft.Cpp.Current.targets(108,3): error MSB4019: 未找到导入的项目“d:\Program Files (x86)\Microsoft Visual Studio\2017\Community\Common7\IDE\VC\VCTargets\Platforms\x64\Platform.targets”。请确认 <Import> 声明中的路径正确，且磁盘上存在该文件。 [C:\Users\xiong\source\build\CMakeFiles\3.9.0-rc3\VCTargetsPath.vcxproj]

        0 个警告
        1 个错误

    已用时间 00:00:00.69


  Exit code: 1



-- Configuring incomplete, errors occurred!
See also "C:/Users/xiong/source/build/CMakeFiles/CMakeOutput.log".

C:\Users\xiong\source\build>
