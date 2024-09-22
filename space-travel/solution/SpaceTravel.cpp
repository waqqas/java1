#include <jni.h>
#include "SpaceTravel.h"

JNIEXPORT jint JNICALL Java_SpaceTravel_measureDistance(JNIEnv *, jobject obj, jdouble s, jdouble t){
   int distance = s*t;
   return distance;
 }
