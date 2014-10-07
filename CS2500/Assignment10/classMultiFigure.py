"""
@author: Matthew Hynes (201200318), CS2500
@date: 21/11/2013

Subclass of FilesFigure class. Includes new unloadfile method and 
loadfile method now accepts line type and colour. Tested using test9.py class
and other data files retrieved from http://www.cs.mun.ca/~brown/2500public
"""

import numpy
from FilesFigure import FilesFigure
from numbers import Number
import matplotlib.pyplot


class MultiFileFigure(FilesFigure):
    
    def __init__(self, *args, **kwargs):
        FilesFigure.__init__(self, *args, **kwargs)
        self.labels = []
    
    def loadfile(self, filenames, linetype='o-'):
        
        self.linetype = linetype
        
        if type(filenames) == list:  # method is run as main module
            for file in filenames:
                x, y = numpy.loadtxt(file, unpack=True)
                
                if not isinstance(x, Number) or not isinstance(y, Number):
                    raise TypeError
                
                self.listofiles.append(file)
                self.theaxes.plot(x, y, linetype, label=file)
                self.labels.append(file)
        else:  # method is called in test9.py
            for file in filenames.split():
                x, y = numpy.loadtxt(file, unpack=True)
                axs = matplotlib.pyplot.axis()
                axisx = axs[1]  # axes max x value
                axisy = axs[3]  # axes max y value
                
                self.listofiles.append(file)
                self.theaxes.plot(x, y, linetype, label=file)
                
                axs2 = matplotlib.pyplot.axis()
                axisx2 = axs2[1]
                axisy2 = axs2[3]
                
                if(axisx / axisx2) < 0.2 or (axisy / axisy2) < 0.2:  # checking that plot range is at least 20% of axes range
                    raise SmallPlotRange
                
                self.labels.append(file)
            
        self.handles, self.labels = self.theaxes.get_legend_handles_labels()
        self.legnd = self.legend(self.handles, self.labels)  
    
    def unloadfile(self, filename):
        # update list of files
        self.listofiles.remove(filename)
        
        # remove plot from graph
        self.theaxes.lines.pop(self.labels.index(filename))
        
        # update legend
        self.labels.remove(filename)
        self.legnd.set_visible(False)
        self.legend(self.handles, self.labels)
        
class SmallPlotRange(Exception):
    def __str__(self):
        print("New plot range too large")
            
if __name__ == "__main__":
    import matplotlib.pyplot as pl
    import sys
    if(len(sys.argv) < 1) :
        print("Data file argument missing")
        exit(1)    
        
    a = pl.figure(sys.argv[1], FigureClass=MultiFileFigure)
    a.loadfile(sys.argv[1:])
    a.show()
    input()
