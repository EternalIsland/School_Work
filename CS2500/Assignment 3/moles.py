#!/usr/bin/python3

import pickle
import re

reagents = open('commonReagents.pickled', 'rb')
periodic = open('periodic.pickled', 'rb')

rec = pickle.load(reagents)
per = pickle.load(periodic)

# list is in format [Name][Formula][Common name][Description]
chems = [c[1] for c in rec]
names = [n[0] for n in rec]

cht = []
molemass = 0
i = 0
cmnname = None

for ch in chems:
    str = ch
    
    for xx in str: #remove water bases from calculation
        if xx == '+':
            n = str.index('+')
            str = str[:-n + 2] 
    
    for x in ['<', '>', ' ', '-']: #remove extra characters for regex processing
        if x in str:
            str = str.replace(x, '')

    str = re.findall('([A-Z][a-z]*)(\d*)', str)

    cht.append(str)   
       
for f in cht:
    for k in f:
        for key in per:
            if k[0] == key:
                m = k[1]
                if m == '':
                    m = m.replace('', '1') #make sure atomic mass isn't multiplied by nothing
                molemass += float(per[key][1]) * int(m)
    
    cmnname = names[i]
    if molemass == 0:
        print('Molar mass of {0} ({1}): {2}'.format(cmnname, rec[i][1], '-'))
    else:
        print('Molar mass of {0} ({1}): {2}'.format(cmnname, rec[i][1], molemass))
        molemass = 0
    
    i += 1
