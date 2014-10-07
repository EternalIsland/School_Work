#!/usr/bin/python3

"""
@author Matthew Hynes (201200318) 
CS 2500, Brown
25/10/2013

This program accepts any number of file inputs in the format of
two-column data points, plots them with a curve, and shows lines
of best fit in linear, quadratic, and cubic forms.
"""

import sys
import numpy as np
import matplotlib.pyplot as pl

def fitPlot(file):
    
    filename = file
    fitfile = open(filename)
    
    x, y = np.loadtxt(fitfile, unpack=True)
    
    # positioning legend out of way of data
    if(filename == 'bucky.dat'):
        legloc = 3
    elif(filename == 'uspop.dat'):
        legloc = 4
    elif(filename == 'cubictest.dat'):
        legloc = 9
    elif(filename == 'novadel.dat'):
        legloc = 2
    
    pl.figure()
    
    pl.plot(x, y)
    
    # getting coefficients of lines
    coeff = np.polyfit(x, y, 1)
    xvals = [np.min(x), np.max(x)]
    yvals = np.polyval(coeff, xvals)
    
    # use matplotlib formatting to display line equations correctly
    fitlab = "$y = {0[0]:5.2f}x + {0[1]:5.2f}$".format(coeff)
    
    pl.plot(xvals, yvals, label=fitlab)
    
    coeff = np.polyfit(x, y, 2)
    xvals = [np.min(x), np.max(x)]
    yvals = np.polyval(coeff, xvals)
    
    fitlab = "$y = {0[0]:5.2f}x^2 + {0[1]:5.2f}x + {0[2]:5.2f}$".format(coeff)

    pl.plot(xvals, yvals, label=fitlab)
    
    coeff = np.polyfit(x, y, 3)
    xvals = [np.min(x), np.max(x)]
    yvals = np.polyval(coeff, xvals)
    
    fitlab = "$y = {0[0]:5.2f}x^3 + {0[1]:5.2f}x^2 + {0[2]:5.2f}x + {0[1]:5.2f}$".format(coeff)
    
    pl.title(filename)
    
    """ default 3rd line colour is light blue, 
        however sometimes the quadratic and cubic lines
        are very similar. So making it a darker colour 
        like magenta ('m')makes it easier to see the differences.
    """
    pl.plot(xvals, yvals, 'm', label=fitlab)
    
    pl.legend(loc=legloc, fontsize=12, fancybox=True, borderaxespad=0)
    
    pl.show()
    
if __name__ == "__main__":

    if len(sys.argv) < 2:
        print("Error: Must provide at least one file to analyze. Separate multiple files with spaces.")
        sys.exit(1)

    files = sys.argv[1:]
    
    for file in files:
        fitPlot(file)
