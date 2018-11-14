cmake_minimum_required(VERSION 3.4.1)


add_library(yqh_bufferio_v0 SHARED src/main/cpp/src/NativeDataIO_V0.cpp )

find_library(log-lib log )

target_link_libraries(yqh_bufferio_v0 ${log-lib} )