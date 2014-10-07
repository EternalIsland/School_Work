"""
@author: Matthew Hynes (201200318), CS2500
@date: 21/11/2013

Subclass of FilesFigure class. Includes new unloadfile method and 
loadfile method now accepts line type and colour. Tested using test9.py class
and other data files retrieved from http://www.cs.mun.ca/~brown/2500public
"""

import numpy
from FilesFigure import FilesFigure


class MultiFileFigure(FilesFigure):
    
    def __init__(self, *args, **kwargs):
        FilesFigure.__init__(self, *args, **kwargs)
        self.labels = []
    
    def loadfile(self, filenames, linetype='o-'):
        
        self.linetype = linetype
        
        if type(filenames) == list:  # method is run as main module
            for file in filenames:
                x, y = numpy.loadtxt(file, unpack=True)
                self.listofiles.append(file)
                self.theaxes.plot(x, y, linetype, label=file)
                self.labels.append(file)
        else:  # method is called in test9.py
            for file in filenames.split():
                x, y = numpy.loadtxt(file, unpack=True)
                self.listofiles.append(file)
                self.theaxes.plot(x, y, linetype, label=file)
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
