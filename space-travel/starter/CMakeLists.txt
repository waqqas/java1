
cmake_minimum_required(VERSION 3.10)
project(SpaceTravel)

# Find JNI package
find_package(JNI REQUIRED)

# Include JNI headers
include_directories(${JNI_INCLUDE_DIRS})

# Add the shared library
add_library(SpaceTravel SHARED SpaceTravel.cpp)

# Link against JNI libraries
target_link_libraries(SpaceTravel ${JNI_LIBRARIES})

# Set the library output name
set_target_properties(SpaceTravel PROPERTIES OUTPUT_NAME "SpaceTravel")

# Set the library version
set_target_properties(SpaceTravel PROPERTIES VERSION 1.0 SOVERSION 1)

# Enable position independent code
set_property(TARGET SpaceTravel PROPERTY POSITION_INDEPENDENT_CODE ON)
