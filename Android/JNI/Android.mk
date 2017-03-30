LOCAL_PATH := $(call my-dir)

#include $(CLEAR_VARS)

#LOCAL_MODULE    := NDKdemolib
#LOCAL_SRC_FILES := NDKdemolib.cpp

#include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE    := c_lib
LOCAL_SRC_FILES := c_lib.c

include $(BUILD_SHARED_LIBRARY)