#!/usr/bin/env python3

import subprocess

print("Launch sub process")

proc = subprocess.Popen(["python3.6", "scripts/fibonacci.py"], stdout=subprocess.PIPE, stderr=subprocess.PIPE)
#proc = subprocess.Popen(["python3.6", "scripts/fibonacci.py"])
proc.wait()
out, err = proc.communicate()
