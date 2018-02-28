#
# This file was derived from the 'Hello World!' example recipe in the
# Yocto Project Development Manual.
#

SUMMARY = "feather-trace-tools"
SECTION = "LITMUS_RT"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
DEPENDS = "glibc linux-litmusrt liblitmus"

URL = "git://github.com/LITMUS-RT/feather-trace-tools.git"
BRANCH = "master"
SRCREV = "0a9701c7be2ef0256bb434d01a9261d9b345bdc6"

SRC_URI = "${URL};protocol=git;branch=${BRANCH}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://0001-Resolve-explicit-dependencies-on-liblitmus-path.patch \
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

#FILES_${PN}-dev += " \
#	    include/litmus.h \
#	    include/migration.h \
#"

FILES_${PN} += " \
	    ftcat \
	    ft-trace-overheads \
	    ftdump \
	    ftsort \
	    ft2csv \
	    st-dump \
	    st-job-stats \
	    st-trace-schedule \
"

do_compile() {
	    oe_runmake LDLIBS=-llitmus
}

do_install() {
	     install -d ${D}${sbindir}
	     install -m 0755 ftcat ${D}${sbindir}
	     install -m 0755 ft-trace-overheads ${D}${sbindir}
	     install -m 0755 ftdump ${D}${sbindir}
	     install -m 0755 ftsort ${D}${sbindir}
	     install -m 0755 ft2csv ${D}${sbindir}
	     install -m 0755 st-dump ${D}${sbindir}
	     install -m 0755 st-job-stats ${D}${sbindir}
	     install -m 0755 st-trace-schedule ${D}${sbindir}
}
