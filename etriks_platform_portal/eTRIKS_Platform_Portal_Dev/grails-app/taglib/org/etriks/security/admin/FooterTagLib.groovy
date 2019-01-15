package org.etriks.security.admin

class FooterTagLib {
def thisYear={
	out<< new Date().format("yyyy")
	}

def copyright={attrs,body->
	out<< "Copyright &copy;"+attrs.startYear+"-"
	out<< thisYear()+""+body()
	
	}
}
