import torch
from openbiolink.obl2021 import OBL2021Evaluator, OBL2021Dataset

def main():
    evaluation_file_path = r"./dataset/prediction"

    dl = OBL2021Dataset()
    ev = OBL2021Evaluator()

    with open(evaluation_file_path) as infile:
        content = infile.readlines()
    content = [x.strip() for x in content]

    predictions = dict()
    for i in range(0, len(content), 3):
        head, rel, tail = content[i].split(" ")
        
        if(content[i+1] == "Heads:"):
            heads = []
        else:
            heads = content[i+1].replace("Heads: ", "").split("\t")[0::2]
            heads = [int(x) for x in heads]
        
        if content[i+2] == "Tails:":
            tails = []
        else:
            tails = content[i+2].replace("Tails: ", "").split("\t")[0::2]
            tails = [int(x) for x in tails]
            
        predictions[int(head), int(rel), int(tail)] = (heads, tails)
        
    top10_tails = torch.full((dl.testing.shape[0],10), -1)
    top10_heads = torch.full((dl.testing.shape[0],10), -1)

    for i in range(dl.testing.shape[0]):
        triple = dl.testing[i,:]
        pred_heads, pred_tails = predictions[triple[0].item(), triple[1].item(), triple[2].item()]
        top10_heads[i,0:len(pred_heads)] = torch.tensor(pred_heads)
        top10_tails[i,0:len(pred_tails)] = torch.tensor(pred_tails)

    res = ev.eval(top10_heads, top10_tails, dl.testing)
    
if __name__ == "__main__":
    main()