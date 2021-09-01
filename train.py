import subprocess
import os
from openbiolink.obl2021 import OBL2021Dataset

working_dir = os.path.abspath('dataset').replace("\\","/")

print(f"Working directory is {working_dir}")

dl = OBL2021Dataset()
dl.save_as_triples()

with open(os.path.join(working_dir, "learn_.txt"),"w") as outfile:
    outfile.write(f"PATH_TRAINING  = {working_dir}/train.txt" + "\n")
    outfile.write(f"PATH_OUTPUT     = {working_dir}/rules" + "\n")
    outfile.write(f"SNAPSHOTS_AT     = 1000" + "\n")
    outfile.write(f"WORKER_THREADS      = 15" + "\n")

subprocess.call([r"java", "-cp", "./AnyBURL-RE/AnyBURL-RE.jar", "de.unima.ki.anyburl.LearnReinforced", os.path.join(working_dir, "learn_.txt")])


with open(os.path.join(working_dir, "apply_.txt"),"w") as outfile:
        outfile.write(f"PATH_TRAINING  = {working_dir}/train.txt" + "\n")
        outfile.write(f"PATH_TEST      = {working_dir}/test.txt" + "\n")
        outfile.write(f"PATH_VALID     = {working_dir}/valid.txt" + "\n")
        outfile.write(f"PATH_RULES     = {working_dir}/rules-1000" + "\n")
        outfile.write(f"PATH_OUTPUT = {working_dir}/prediction" + "\n")
        outfile.write(f"TOP_K_OUTPUT = 10" + "\n")

subprocess.call([r"./SAFRAN/build/Release/SAFRAN", "applymax", os.path.join(working_dir, "apply_.txt")])