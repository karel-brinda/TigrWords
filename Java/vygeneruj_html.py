import os
import functools
import itertools
import copy
import sys

def htmlZacatek(titulek):
	return """<html><head><style>
		td {vertical-align:top;text-align:center;}
		.sloupec {width:140px}
		.sloupecMaly {width:50px}
		.rpv {display:none}
		</style>
		<script language="JavaScript">
			function show_hide_column(col_no, do_show) {
			
				var stl;
				if (do_show) stl = 'block'
				else         stl = 'none';
					
				var tbl  = document.getElementById('id_of_table1');
				var rows = tbl.getElementsByTagName('tr');
					
				for (var row=0; row<rows.length;row++) {
					var cels = rows[row].getElementsByTagName('td')
					cels[col_no].style.display=stl;
				}
				
				tbl  = document.getElementById('id_of_table2');
				rows = tbl.getElementsByTagName('tr');
					
				for (var row=0; row<rows.length;row++) {
					var cels = rows[row].getElementsByTagName('td')
					cels[col_no].style.display=stl;
				}

			}
		</script>
		<title>"""+titulek+"""</title></head><body>""";

def htmlKonec():
	return " </body></html>";
	
def polozkaTabulky(vlevo,vpravo=""):
	if vpravo=="":
		return "<td>"+str(vlevo)+"</td>"
	else:
		return "<td>"+str(vlevo)+"</td><td>"+str(vpravo)+"</td>"
		
def zformatujVektor(vektor):
	return ("("+str(vektor)[1:-1]+")").replace(" ","")
	
def zformatujVektory(vektory):
	return "("+(str(vektory).replace(" ","").replace("],[",")<br />("))[2:-2]+")"
	
def greedyAlgorithm (cislo, baz):
	rozvoj = []

	baze = copy.deepcopy(baz)
	jesteNuly = 1
	baze.reverse()
	for bazickeCislo in baze:
		cislice = cislo // bazickeCislo
		if cislice != 0:
			jesteNuly = 0
		if not jesteNuly or cislice != 0:
			rozvoj.append(cislice)
		cislo = cislo % bazickeCislo

	if rozvoj == []:
		rozvoj = [0]

	return rozvoj

def main():
	if len(sys.argv) == 3:
		fileFrom=sys.argv[1]
		fileTo=sys.argv[2]
		print (fileFrom, fileTo)
		fileRead, fileWrite2 = open(fileFrom,"r"), open(fileTo,"w")
		fileReadList = list(fileRead)
		fileRead_dictionary = list(filter(lambda x:x[0]=="#",  [x.strip() for x in fileReadList]))
		fileRead_parikhVectors = list(itertools.filterfalse(lambda x:x[0]=="#",[x.strip() for x in fileReadList]))
		
		parikhVectorsAllNs = [[[int(z) for z in y.split(",")] for y in (lineForN.strip()[1:-1]).split("),(")] for lineForN in fileRead_parikhVectors]
		dictionary = dict([ (x.partition("=")[0][1:],x.partition("=")[2]) for x in fileRead_dictionary])
		dictionary["iterationLengths"] = [int(x) for x in dictionary["iterationLengths"][1:-1].split(", ")]
		
		outProm2=htmlZacatek("titulek")
		outProm2+="""
		
		**TO_BE_REPLACED**
		
		<button onClick="javascript:show_hide_column(5,true);">Show column with relative Parikh vectors</button>
		<button onClick="javascript:show_hide_column(5,false);">Hide column with relative Parikh vectors</button>
		
		<table id="id_of_table1">
			<tr style="font-weight:bold">
				<td class="sloupecMaly">n</td>
				<td class="sloupec">expansion of n</td>
				<td class="sloupecMaly">AC(n)</td>
				<td class="sloupecMaly">B(n)</td>
				<td class="sloupec">Parikh vector of prefix</td>
				<td class="sloupec rpv">Relative Parikh vectors</td>
				<td class="sloupec">min entries of rel. Parikh vectors</td>
				<td class="sloupec">max entries of rel. Parikh vectors</td>
				<td class="sloupec">max - min entries</td>
			</tr>         
		</table>
		<div style="overflow: auto;height: 500px;">
		<table id="id_of_table2">"""
		
		abelianComplexitySet=set()
		balanceFunctionSet=set()
		
		for parikhVectorsOneN in parikhVectorsAllNs:
			abelianComplexity=len(parikhVectorsOneN)
			firstParikhVector=parikhVectorsOneN[0]
			n = functools.reduce(lambda x,y:x+y,firstParikhVector)
			relativeParikhVectors = [[y-x for (x,y) in zip(firstParikhVector,parikhVector)] for parikhVector in parikhVectorsOneN]
			listOfEntries = zip(*relativeParikhVectors)
			zipped = list(zip(*relativeParikhVectors))
			minEntriesOfRelativeParikhVector=[min(x) for x in zipped]
			maxEntriesOfRelativeParikhVector=[max(x) for x in zipped]
			difference = [x-y for [x,y] in zip(maxEntriesOfRelativeParikhVector,minEntriesOfRelativeParikhVector)]
			
			abelianComplexitySet.add(abelianComplexity)
			balanceFunctionSet.add(max(difference))


			outProm2+="""<tr>"""
			outProm2+= """<td class="sloupecMaly">"""+str(n)+"""</td>"""
			outProm2+= """<td class="sloupec">"""+str(greedyAlgorithm(n,dictionary["iterationLengths"]))[1:-1]+"""</td>"""
			outProm2+= """<td class="sloupecMaly">"""+str(abelianComplexity)  + """</td>"""
			outProm2+= """<td class="sloupecMaly">"""+str(max(difference)) + """</td>"""
			outProm2+= """<td class="sloupec">""" + zformatujVektor(firstParikhVector) + """</td>"""
			outProm2+= """<td class="rpv sloupec">""" +zformatujVektory(relativeParikhVectors) + """</td>"""
			outProm2 += """<td class="sloupec">""" + zformatujVektor(minEntriesOfRelativeParikhVector) + """</td>"""
			outProm2 += """<td class="sloupec">""" + zformatujVektor(maxEntriesOfRelativeParikhVector) + """</td>"""
			outProm2 += """<td class="sloupec">""" + zformatujVektor(difference)+ """</td>"""
			outProm2+="""</tr>\n"""

		outProm2+="""</tbody></table></div>"""
		outProm2+=htmlKonec()                                                                        
		toAddToOutput = ""
		toAddToOutput += "AC(n) &isin; " + str(abelianComplexitySet) + ", "
		toAddToOutput += "B(n) &isin; " + str(balanceFunctionSet) + ", "
		toAddToOutput += "U = " + str([x for x in dictionary["iterationLengths"] if x <= len( parikhVectorsAllNs)])
		toAddToOutput += "<br />"
		print(outProm2.replace("**TO_BE_REPLACED**",toAddToOutput),file=fileWrite2)
		fileRead.close(), fileWrite2.close()
	else:
		print("Wrong parameters")
			
main()
	