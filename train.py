import subprocess
import os
from openbiolink.obl2021 import OBL2021Dataset

def main():

    working_dir = os.path.abspath('obl2021').replace("\\","/")

    print(f"Working directory is {working_dir}")

    dl = OBL2021Dataset()

    # Learn rules with AnyBURL
    with open(os.path.join(working_dir, "learn_.txt"),"w") as outfile:
        outfile.write(f"PATH_TRAINING  = {working_dir}/train.tsv" + "\n")
        outfile.write(f"PATH_OUTPUT     = {working_dir}/rules" + "\n")
        outfile.write(f"SNAPSHOTS_AT     = 1000" + "\n")
        outfile.write(f"WORKER_THREADS      = 22" + "\n")
    subprocess.call([r"java", "-cp", "./AnyBURL-RE/AnyBURL-RE.jar", "de.unima.ki.anyburl.LearnReinforced", os.path.join(working_dir, "learn_.txt")])

    # Apply rules (Maximum aggregation)
    with open(os.path.join(working_dir, "apply_.txt"),"w") as outfile:
            outfile.write(f"PATH_TRAINING  = {working_dir}/train.tsv" + "\n")
            outfile.write(f"PATH_TEST      = {working_dir}/test.tsv" + "\n")
            outfile.write(f"PATH_VALID     = {working_dir}/valid.tsv" + "\n")
            outfile.write(f"PATH_RULES     = {working_dir}/rules-1000" + "\n")
            outfile.write(f"PATH_OUTPUT = {working_dir}/prediction_max" + "\n")
            outfile.write(f"TOP_K_OUTPUT = 10" + "\n")
    subprocess.call([r"./SAFRAN/build/Release/SAFRAN", "applymax", os.path.join(working_dir, "apply_.txt")])
    
    
    # Calculate Jaccard indices
    with open(os.path.join(working_dir, "jacc_.txt"),"w") as outfile:
            outfile.write(f"PATH_TRAINING  = {working_dir}/train.tsv" + "\n")
            outfile.write(f"PATH_TEST      = {working_dir}/test.tsv" + "\n")
            outfile.write(f"PATH_VALID     = {working_dir}/valid.tsv" + "\n")
            outfile.write(f"PATH_RULES     = {working_dir}/rules-1000" + "\n")
            outfile.write(f"PATH_JACCARD  = {working_dir}" + "\n")
            outfile.write(f"RESOLUTION     = 200" + "\n")
    subprocess.call([r"./SAFRAN/build/Release/SAFRAN", "calcjacc", os.path.join(working_dir, "jacc_.txt")])

    # Learn NRNO cluster (random search)
    with open(os.path.join(working_dir, "learn_.txt"),"w") as outfile:
        outfile.write(f"PATH_TRAINING  = {working_dir}/train.tsv" + "\n")
        outfile.write(f"PATH_TEST      = {working_dir}/test.tsv" + "\n")
        outfile.write(f"PATH_VALID     = {working_dir}/valid.tsv" + "\n")
        outfile.write(f"PATH_RULES     = {working_dir}/rules-1000" + "\n")
        outfile.write(f"PATH_JACCARD    = {working_dir}" + "\n")
        outfile.write(f"DISCRIMINATION_BOUND = 1000" + "\n")
        outfile.write(f"TOP_K_OUTPUT = 50" + "\n")
        outfile.write(f"PATH_CLUSTER = {working_dir}/cluster_nrno" + "\n")
        outfile.write(f"STRATEGY = random" + "\n")
        outfile.write(f"ITERATIONS = 10000" + "\n")
        outfile.write(f"RESOLUTION = 10" + "\n")
        outfile.write(f"SEED = 0" + "\n")
    subprocess.call([r"./SAFRAN/build/Release/SAFRAN", "learnnrnoisy", os.path.join(working_dir, "learn_.txt")])
            
    
    # Apply NRNO (random search)
    with open(os.path.join(working_dir, "apply_.txt"),"w") as outfile:
        outfile.write(f"PATH_TRAINING  = {working_dir}/train.tsv" + "\n")
        outfile.write(f"PATH_TEST      = {working_dir}/test.tsv" + "\n")
        outfile.write(f"PATH_VALID     = {working_dir}/valid.tsv" + "\n")
        outfile.write(f"PATH_RULES     = {working_dir}/rules-1000" + "\n")
        outfile.write(f"PATH_CLUSTER = {working_dir}/cluster_nrno" + "\n")
        outfile.write(f"PATH_OUTPUT = {working_dir}/prediction_nrno" + "\n")
        outfile.write(f"WORKER_THREADS = -1" + "\n")
        outfile.write(f"TOP_K_OUTPUT = 10" + "\n")
    subprocess.call([r"./SAFRAN/build/Release/SAFRAN", "applynrnoisy", os.path.join(working_dir, "apply_.txt")])
    
if __name__ == "__main__":
    main()