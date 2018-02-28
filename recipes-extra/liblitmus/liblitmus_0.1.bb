#
# This file was derived from the 'Hello World!' example recipe in the
# Yocto Project Development Manual.
#

SUMMARY = "liblitmus"
SECTION = "LITMUS_RT"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
DEPENDS = "glibc linux-litmusrt"

inherit kernel-arch

URL = "git://github.com/LITMUS-RT/liblitmus.git"
BRANCH = "master"
SRCREV = "31352298446e1fc1ba4dc391197aef3fd57bf080"

SRC_URI = "${URL};protocol=git;branch=${BRANCH}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://0001-Forcibly-add-GNU-HASH-linker-flag.patch \
"

S = "${WORKDIR}/git"

PV = "2017.1+git${SRCPV}"

inherit kernel-arch

# Skip processing of this recipe if linux-litmusrt is not explicitly specified as the
# PREFERRED_PROVIDER for virtual/kernel.
python () {
    if d.getVar("PREFERRED_PROVIDER_virtual/kernel", True) != "linux-litmusrt":
        raise bb.parse.SkipPackage("Set PREFERRED_PROVIDER_virtual/kernel to linux-litmusrt to enable it")
}

FILES_${PN}-dev += " \
	    include/litmus.h \
	    include/migration.h \
"

FILES_${PN}-staticdev += " \
	    liblitmus.a \
"

do_compile() {
	    oe_runmake LITMUS_KERNEL=${STAGING_KERNEL_DIR}
}

do_install() {
	     install -d ${D}${sbindir}
	     install -m 0755 base_mt_task ${D}${sbindir}
	     install -m 0755 base_task ${D}${sbindir}
	     install -m 0755 cycles ${D}${sbindir}
	     install -m 0755 measure_syscall ${D}${sbindir}
	     install -m 0755 release_ts ${D}${sbindir}
	     install -m 0755 resctl ${D}${sbindir}
	     install -m 0755 rt_launch ${D}${sbindir}
	     install -m 0755 rtspin ${D}${sbindir}
	     install -m 0755 runtests ${D}${sbindir}
	     install -m 0755 setsched ${D}${sbindir}
	     install -m 0755 showsched ${D}${sbindir}
	     install -m 0755 uncache ${D}${sbindir}
	     install -d ${D}${libdir}
	     install -m 0644 liblitmus.a ${D}${libdir}
	     install -d ${D}${includedir}
	     install -m 0644 include/litmus.h ${D}${includedir}
	     install -m 0644 include/migration.h ${D}${includedir}
}
