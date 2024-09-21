
cmake_minimum_required(VERSION 3.10)
project(SubtractionFun)

# Find JNI package
find_package(JNI REQUIRED)

# Include JNI headers
include_directories(${JNI_INCLUDE_DIRS})

# Add the shared library
add_library(SubtractionFun SHARED SubtractionFun.cpp)

# Link against JNI libraries
target_link_libraries(SubtractionFun ${JNI_LIBRARIES})

# Set the library output name
set_target_properties(SubtractionFun PROPERTIES OUTPUT_NAME "SubtractionFun")

# Set the library version
set_target_properties(SubtractionFun PROPERTIES VERSION 1.0 SOVERSION 1)

# Enable position independent code
set_property(TARGET SubtractionFun PROPERTY POSITION_INDEPENDENT_CODE ON)
