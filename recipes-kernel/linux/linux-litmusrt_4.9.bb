DESCRIPTION = "Litmus-RT Linux kernel "

require recipes-kernel/linux/linux-yocto.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"
COMPATIBLE_MACHINE = "aarch64"

BRANCH = "linux-4.9-litmus"
SRCREV = "4acc377593580e7d04ad8b42b258e8c2b39030ee"

SRC_URI = "git://github.com/LITMUS-RT/litmus-rt.git;protocol=git;branch=${BRANCH}"

LINUX_VERSION ?= "4.9.30"
PV = "${LINUX_VERSION}+git${SRCPV}"
PR = "r1"

SRC_URI_append = " \
    file://defconfig \
"
