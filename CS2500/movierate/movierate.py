#!/usr/bin/python3


import sys
import os
import time

if len(sys.argv) != 2 :
    print('Incorrect usage. Format:{0} [movie name]'.format(sys.argv[0]))
    sys.exit(1)

# moviedir = 'datasets/movielens/'
fmovie = open('/users/cs/study/mrh830/cs2500/movies.dat', 'r')
frats = open('/users/cs/study/mrh830/cs2500/ratings.dat', 'r')
fusr = open('/users/cs/study/mrh830/cs2500/users.dat', 'r')
logfile = 'access.log'

# Look up a movie and its average rating
# get the movie-id

movid = None
for movieline in fmovie :
    descriptlist = movieline.split('::')
    if sys.argv[1] in descriptlist[1] :
        if input("do you want: " + descriptlist[1] + "? [y/n] ") == 'y' :
            movid = descriptlist[0]
            fmovie.close()
            break

if movid == None :
    print(sys.argv[1], ': Can not find movie listing')
    sys.exit(1)

rcount = 0
raccum = 0
mcount = 0
maccum = 0
fcount = 0
faccum = 0
usrid = None

# accumulate the ratings
    
for ratline in frats:  
    ratlist = ratline.split('::')
        
    if ratlist[1] == movid :
        rcount += 1
        raccum += int(ratlist[2])
        usrid = ratlist[0] 
        
        for usrline in fusr:  
            usrlist = usrline.split('::') 
    
            if usrid in usrlist:
                if usrlist[1] == 'M':
                    mcount += 1
                    maccum += int(ratlist[2])
                elif usrlist[1] == 'F':
                    fcount += 1 
                    faccum += int(ratlist[2]) 

print("{0} ratings averaging {1:.1f} out of 5, with a {2:.1f} average rating for males and {3:.1f} average rating for females.".format(rcount,
                                                       raccum / max(1, rcount), maccum / max(1, mcount), faccum / max(1, mcount)))

# log the access
print("[{0}] access by '{1}'".format(time.asctime(), os.environ['USER']),
      file=open(logfile, 'a'))
