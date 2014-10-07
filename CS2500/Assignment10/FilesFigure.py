#!/usr/bin/python3
import matplotlib
import matplotlib.pyplot
import numpy


class FilesFigure(matplotlib.pyplot.Figure):

    def __init__(self, *args, **kwargs):
        # super(self.__class__,self).__init__(*args, **kwargs)
        matplotlib.pyplot.Figure.__init__(self, *args, **kwargs)
        self.listofiles = []
        self.theaxes = self.add_subplot(111)
          
    def loadfile(self, fname):
        """ add the file data and plot to the main axes of
            this figure """
        x, y = numpy.loadtxt(fname, unpack=True)
        self.listofiles.append(fname)
        self.theaxes.plot(x, y)

if __name__ == "__main__":
    import matplotlib.pyplot as pl
    import sys
    if(len(sys.argv) < 1) :
        print("Data file argument missing")
        exit(1)    
        
    a = pl.figure(sys.argv[1], FigureClass=FilesFigure)
    a.loadfile(sys.argv[1])
    a.show()
    input()

        
        
