#~/bin/sh

export ANDROID_NDK="`pwd`/ndk"
export TOOLCHAIN_PATH=${ANDROID_NDK}/bin
export PATH="${TOOLCHAIN_PATH}:$PATH"
export CROSS_COMPILE="${TOOLCHAIN_PATH}/arm-linux-androideabi-"
export CC="${CROSS_COMPILE}gcc"
export CFLAGS="${CFLAGS} -fPIE -pie"
export LDFLAGS="${LDFLAGS} -fPIE -pie"


${CROSS_COMPILE}gcc ${CFLAGS} ${LDFLAGS} hello.c -o ndkhello
echo "Done"
