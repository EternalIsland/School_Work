#!/usr/bin/python3

"""
CS2500, 2013
Matthew Hynes (201200318)
"""

import sys
import re
from urllib.parse import urlparse


"""
Second argument is called protocols 
to fit assignment structure. 
But it should actually be called
'schemes' for accuracy.
"""
def search(src, protocols=[], domains=[]):
    r = re.compile('<a href=(.*?)>')
    srcf = r.findall(src)
    for c in srcf:
        c.strip('"')
    url = urlparse(srcf[0])
    urltup = None
    
    scheme = url.scheme
    domain = url.netloc
    
    if protocols and scheme in protocols:
        if domains and domain in domains:
            urltup = (scheme, url.geturl())
        elif not domains:
            urltup = (scheme, url.geturl())
            
    elif domains and domain in domains:
        if protocols and scheme in protocols:
            urltup = (scheme, url.geturl())
        elif not protocols:
            urltup = (scheme, url.geturl())    
    
    elif not protocols and not domains:
        urltup = (scheme, url.geturl())
        
    return urltup
    
    
if __name__ == "__main__" :
    if len(sys.argv) != 3:
        print("Usage error. Format: {0} '{1}' '{2} '".format(sys.argv[0], 'file', 'domains'))
        sys.exit(1)

    file = open(sys.argv[1])
    dom = sys.argv[2:]
    urline = 0
    for line in file:
        # assuming each line in file is of format: '<a href=URL>'
        urli = line
        search(urli, domains=dom)
    
