#!/usr/bin/python3

"""
@author Matthew Hynes (201200318) 
CS 2500, Brown
25/10/2013

Reads a file with 2 or more columns. X values, Y values, and then an optional annotation, and 
outputs the plotted values and allows the user to dynamically edit them in the text box. 
Use open to fill the text box with a file and then press 'Plot values' to plot graph.

*Please see comment about best fit line*
"""

import tkinter.filedialog
import numpy as np
import matplotlib.pyplot as pl
import tkinter as tki

root = tki.Tk()
root.wm_title("Regression Annotator")

def openfile():
        """modified boilerplate code taken 
           from CS2500 lecture 17 notes
        """
        
        global textarea
        filename = tkinter.filedialog.askopenfilename()
        
        textarea.delete(1.0, tki.END)
        textarea.insert(1.0, open(filename).read())
        
def plotvalues():
    fig = pl.figure()
    xlist = []
    ylist = []
    
    # TODO best fit, annotate, if provided 
    text = textarea.get(1.0, tki.END)
    label = ' '
    axes = fig.add_subplot(111)
        
    for line in text.split('\n'):
        if line:
            point = line.split(maxsplit=2)
            x = point[0]
            y = point[1]
            
            xlist.append(x)
            ylist.append(y)
           
            if(len(point) > 2):
                axes.annotate(point[2:], xy=(x, y), xycoords='data',
                            xytext=(-70, 30), textcoords='offset points',
                            arrowprops=dict(arrowstyle="->")
                            )
                
            pl.scatter(x, y)
        
    # arrays of x and y points    
    xarr = np.array(xlist)
    yarr = np.array(ylist)
    
    print("X values: ", xarr)
    print("Y values: ", yarr)
    
    
    """I was unable to get the best fit line to come up because the following line, taken from the class notes, produced the error: 
        TypeError: unsupported operand type(s) for +: 'numpy.ndarray' and 'float'. Even using a list instead of 
        a numpy array resulted in the same error. I have included printouts of the arrays for the x and y variables 
        so you can see their contents and perhaps find out why this was happending. The other commented lines were part of
        the best fit calculation but relied on the coefficients first.
    """
    # error: coeffs = np.polyfit(xarr, yarr, 1)
    # xvals = [np.min(xarr), np.max(xarr)]
    # yvals = np.polyval(coeffs, xvals)
    
    # label = "y = {0[0]:5.2f}x + {0[1]:5.2f}".format(coeffs)
    # pl.plot(xvals, yvals, label)
    #pl.legend()
    
    fig.show()
        

if __name__ == '__main__':
    
    textarea = tki.Text(root, height=35, width=50)
    textarea.pack(side=tki.LEFT, fill=tki.BOTH, expand=tki.YES)
    
    scroll = tki.Scrollbar(root, command=textarea.yview)
    scroll.pack(side=tki.RIGHT, fill=tki.Y)
    textarea.config(yscrollcommand=scroll.set)
    
    mainmenu = tki.Menu(root)
    
    filemenu = tki.Menu(mainmenu, tearoff=0)
    filemenu.add_command(label="Open", command=openfile)
    filemenu.add_command(label="Exit", command=root.quit)
    mainmenu.add_cascade(label="File", menu=filemenu)
    
    plotmenu = tki.Menu(mainmenu, tearoff=0)
    plotmenu.add_command(label="Plot Values", command=plotvalues)
    mainmenu.add_cascade(label="Plot", menu=plotmenu)
    
    root.config(menu=mainmenu)
   
    root.mainloop()
