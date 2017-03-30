#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_example_ndkdemo_MainActivity_stringFromCJNI( JNIEnv* env, jobject thiz )
{
	return (*env)->NewStringUTF(env, "Hello from JNI !");
}
