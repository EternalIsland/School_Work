#!/usr/bin/python3

import pickle

reagents = open('periodic.pickled', 'wb')
perfile = "periodictabledump.csv"

pertab = {}

for line in open(perfile):
    ele = line.strip().split(',')
    pertab[ele[3].strip('"')] = (int(ele[0].strip('"')),
                  float(ele[1].strip('"')),
                  ele[2].strip('"'))
    
pickle.dump(pertab, reagents)
reagents.close()

