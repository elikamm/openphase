.PHONY: all run
source_files := $(wildcard src/*.java)
manifest_file := src/MANIFEST.MF

all: build/openphase.jar

run: all
	java -jar build/openphase.jar

test: all
	java -jar build/openphase.jar -- test/bibliothek.csv

build/openphase.jar: $(source_files)
	javac -verbose -d build/classfiles $(source_files)
	jar --create --verbose --file=build/openphase.jar --manifest=$(manifest_file) -C build/classfiles .