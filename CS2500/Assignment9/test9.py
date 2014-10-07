#!/usr/bin/python3
import matplotlib
import matplotlib.pyplot as pl
import os
from classMultiFigure import MultiFileFigure

"""
Testing Suite for Assignment 9, 2500CS F 2013
interactively select data plot files from a directory.
"""

if __name__ == "__main__":
    import matplotlib.pyplot as pl
    import sys
    if(len(sys.argv) < 2) :
        print("Directory argument missing")
        exit(1)
        
    # create list of files and is_plotted flags
    finfo = os.listdir(sys.argv[1])
    print("Available files:")
    for i, fn in enumerate(finfo):
        print("{0:3d} {1}".format(i, fn))

    pl.interactive(True)
    thefig = pl.figure(sys.argv[1], FigureClass=MultiFileFigure)
    thefig.show()

    while 1:
        # post menu to user
        uchoice = input("Which file to plot? [blank to exit] ")
        if not uchoice: break;
        i = int(uchoice.strip())
        fname = sys.argv[1] + "/" + finfo[i]
        if fname in thefig.listofiles:
            yesno = input(fname + " already plotted. Remove plot? [y/n] ")           
            if yesno.strip()[0] == 'y':
                print("Removing plot ", fname)
                thefig.unloadfile(fname)
            else :
                print("No action taken")
        else:
            print("plotting file", fname)
            thefig.loadfile(fname, "o-")
        thefig.canvas.draw()
