#!/usr/bin/python3

import sys

if len(sys.argv) != 2:
    print('Incorrect usage. Format:{0} [name]'.format(sys.argv[0]))
    sys.exit(1)

i = 1880
maxnamesrel = 0
maxnamesunrel = 0
maxname = None
maxyear = 0
maxgender = None
while i <= 2010:
    yearname = '/users/cs/study/mrh830/cs2500/yob{0}.txt'.format(i)
    yearfile = open(yearname + '', 'r')
    for line in yearfile:
        namelist = line.strip('\n')
        namelist = namelist.split(',')
        if namelist[0] == sys.argv[1]:
                
            nameoccur = int(namelist[2])
            if nameoccur > int(maxnamesrel):
                maxnamesrel = namelist[2]
                maxyear = i 
                maxgender = namelist[1]
                
        maxnm = int(namelist[2])        
        if int(maxnamesunrel) <= maxnm and namelist[1] == maxgender:
            maxnamesunrel = namelist[2]
            maxname = namelist[0]        
            
    i += 1
    yearfile.close()
if maxnamesrel == 0:
    print('Name not found.')
    sys.exit(0)   
    
print('Most popular year for {0}: {1}. {2} occurences.'.format(sys.argv[1], maxyear, maxnamesrel))
print('Most popular name in {0}: {1}, {2} occurences'.format(maxyear, maxname, maxnamesunrel))
