SUMMARY = "A small image just able to boot and run LITMUS-RT."

PREFERRED_PROVIDER_virtual/kernel = "linux-litmusrt"

IMAGE_INSTALL = " \
    packagegroup-core-boot \
    bash \
    dhcp-client \
    liblitmus \
    feather-trace-tools \
"

IMAGE_FEATURES += "ssh-server-openssh"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image

IMAGE_ROOTFS_SIZE = "8192"

IMAGE_FSTYPES = "cpio.gz"

SERIAL_CONSOLE = "115200 hvc0"


