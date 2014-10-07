#!/usr/bin/python3

"""
@author Matthew Hynes (201200318) 
CS 2500, Brown
@date 1/11/2013

This program plots a regression line of data given in the file input and shows the 95% confidence interval for each point. 
It then identifies the outlier, with the Cook's Distance caused by deleting it, and plots another regression line with the outlier removed. 
The user has the option to save the data set without the outlier.
"""

import sys
import math
import numpy as np
import matplotlib.pyplot as pl
import scipy.stats as st



def poly_ci(x, y) :
    """wrapper for the np.polyfit least squares linear fit,
    returns polyval values, and also returns a function to give
    the ci for estimate of y for any point on the line
    """
    
    n = len(x)
    fit = np.polyfit(x, y, 1, full=True)
    coeffs = fit[0]
    SSE = fit[1][0]
    MSE = SSE / (n - 2)
    Xmean = np.mean(x)
    SSX = np.sum((x - Xmean) ** 2)
    tvar = st.t(n - 2)

    def yci(x, p=0.05) :
        """value of fitted function and size of
           confidence interval for fitted y at x
        """
        yfit = np.polyval(coeffs, x)
        y_err = np.sqrt(MSE * (1 / n + (x - Xmean) ** 2 / SSX))
        ci = tvar.ppf(p / 2) * y_err
        
        # scaling y errors with 95% confidence interval 
        global y_outliers; y_outliers = yfit * y_err
        
        return (yfit, -ci)
    return(fit, yci)

if __name__ == "__main__":
    
    x, y = np.loadtxt(sys.argv[1], unpack=True)

    x = np.array(x)
    y = np.array(y)

    fit, ci_func = poly_ci(x, y)
    yi_fit, ci = ci_func(x)
    
    # biggest number in scaled errors is the outlier
    outlier = y_outliers.max()
    
    # index of outlier
    outloc = np.where(y_outliers == outlier)
    
    fig = pl.figure()
    
    axes = fig.add_subplot(111)
    axes.plot(x, y, 'o')
    axes.plot(x, yi_fit, label="Regression line") 
    axes.errorbar(x, yi_fit, yerr=ci, label="95% confidence interval")
    
    # x and y coords without outlier
    yminout = np.delete(y, outloc)
    xminout = np.delete(x, outloc)
    
    fit, ci_func = poly_ci(xminout, yminout)
    yi_fit, ci = ci_func(xminout)
    
    axes.plot(xminout, yi_fit, label="Regression line (outlier removed)")
    
    outfit = np.polyfit(xminout, yminout, 1, full=True)
    SSEout = fit[1][0]
    MSEout = SSEout / (len(xminout) - 2)
    
    #calculating Cook's Distance from second formula at http://en.wikipedia.org/wiki/Cook%27s_distance
    cooksd = (SSEout / len(yi_fit) * MSEout) * (y[outloc] / math.pow(1 - y[outloc], 2))
    
    outlab = "Outlier (Cook's D: {0}".format(cooksd)
    
    axes.annotate(outlab, xy=(x[outloc], y[outloc]), xycoords='data',
                xytext=(30, 15), textcoords='offset points', bbox=dict(boxstyle="round", fc="0.8"),
                arrowprops=dict(arrowstyle="->")
                )
    
    pl.legend(loc=4, fontsize=10)
    
    pl.title("Outlier and Cook's Distance")
    
    fig.show()
    
    save = input("Save new data set? (y/n)")
    
    if save == "y":
        np.savetxt('youtlier', yminout)
        
    
