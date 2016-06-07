	module tool
	
	import IO;
	import lang::java::m3::Core;
	import lang::java::jdt::m3::Core;
	import lang::java::jdt::m3::AST;
	import analysis::m3::Registry;
	import List;
	import Set;
	import String;
	import Map;
	import Node;
	import lang::java::\syntax::Java15;
	import IO;
	import ParseTree; //module Idiomatic
	import util::Math;
	
	//-------------------------------------------------------	
	
	
	public value numberOfFirstLevelNested(loc l){
	list[loc] locs = [];
	int nst = 0;
	m = createM3FromEclipseProject(l);
	mthds = toList(methods(m));
	int i = 0;
	
	for (mtd <- mthds){
	
		methAST = getMethodASTEclipse(mtd, model=m);
			for (/Statement s <-methAST){
		
			if (\if(a,b) :=s){   
			println(1); 			
			if (\block(st) := b){
			println(1.2);
			for (ststm <-st){
			println(1.3);
			println(ststm);
			if (\if(c,d,e) := ststm){ 
						println(d@src); println(e@src); 
nst = nst + countLOC(c@src);
			nst = nst + countLOC(d@src);
			println(2);
			}
						else if (\if(t,r) := ststm){			
						println(3);
						println(r@src);						
						nst = nst + countLOC(r@src);
						};
			}
			
locs = locs + b@src;
}

else{
if (\if(x,y,z) := b){
println(4); 
println(y@src); println(z@src);

nst = nst + countLOC(y@src) + countLOC(z@src);
}

if (\if(x,y) := b){//println(readFile(y@src));
println("5.5");
nst = nst + countLOC(y@src);
}
locs = locs + b@src;
}
			i = i + 1;
	}
	if (\if(a,b,c) :=s){			
			
			locs = locs + c@src;
			locs = locs + b@src;
			
			if (\block(st2) := b){
			for (ststm <-st2){
			if (\if(d,e,f) := ststm){
			println(ststm@src);
			println(6.0);
			println(countLOC(ststm@src));
			nst = nst + countLOC(ststm@src);
			}
else 						if (\if(t,r) := ststm){			
			println(r@src);
			println(7.0);
			println(countLOC(ststm@src));
						nst = nst + countLOC(ststm@src);
						};
			}}

if (\block(st3) := c){
			for (ststm <-st3){
			if (\if(f,g,h) := ststm){
			println(ststm@src);
			println(countLOC(ststm@src));
			nst = nst + countLOC(ststm@src);
			};
						if (\if(h,j) := ststm){
			println(ststm@src);
			println(countLOC(ststm@src));
						nst = nst + countLOC(ststm@src);
						};
			}}
						
						i = i + 1;
	}
	}
	}
	
	set[loc] stmts = {tt|ll <- locs,tt<-locs,tt.begin[0] >= ll.begin[0] && tt.end[0] <= ll.end[0] && tt != ll};
	list[loc] unique = [lll|lll<-locs, !(lll in stmts)];
for (stm <- stmts){;//println(stm);
}
int linsofcode = 0;
println(nst);
return true;}
	
	public value numberOfCondit(loc l){
	list[loc] locs = [];
	m = createM3FromEclipseProject(l);
	mthds = toList(methods(m));
	int i = 0;
	
	for (mtd <- mthds){
	
		methAST = getMethodASTEclipse(mtd, model=m);
	
		for (/Statement s <-methAST){
	
			if (\if(a,b) :=s){

			if (\block(st) := b){
print("if,");
locs = locs + b@src;
}
else{
locs = locs + b@src;
}
			i = i + 1;
	}
	if (\if(a,b,c) :=s){
			print("if else,");
			locs = locs + c@src;
			locs = locs + b@src;
			i = i + 1;
	}
	}
	}
	
	for(loc1 <- locs){
	;
	}
	set[loc] stmts = {tt|ll <- locs,tt<-locs,tt.begin[0] >= ll.begin[0] && tt.end[0] <= ll.end[0] && tt != ll};
	list[loc] unique = [lll|lll<-locs, !(lll in stmts)];
println(size("unique"));
int linsofcode = 0;
for (qq <- stmts){
;}
for(llm <- unique){
	linsofcode = linsofcode + (countLOC(llm));
	}
	println(linsofcode);
	println(i);
	return true;}	
	
		public value numberOfIfs(loc l){
	
	m = createM3FromEclipseProject(l);
	mthds = toList(methods(m));
	int i = 0;
	
	for (mtd <- mthds){
	
		methAST = getMethodASTEclipse(mtd, model=m);
	
		for (/Statement s <-methAST){
	
			if (\if(a,_) :=s){
			println(readFile(a@src));
			println((a@src));
			i = i + 1;
	}
	if (\if(a,_,_) :=s){
			println(readFile(a@src));
			println((a@src));
			i = i + 1;
	}
	}
	}
	return i;
	}
		
		// To count total LOC for a model (not used in analysis).
	// @param model: M3 model to count lines for.
	// @return: number of lines in model.
	public int countM3LOC(M3 model) {
		return (0 | it + countLOC(file) | file <- files(model));
	}
	
	// To count lines of code for a given project (loads M3 model).
	// @param project: location of project to count lines for.
	// @return: number of lines for project.
	public int projectLOC(loc project) {
		model = createM3FromEclipseProject(project);
		println("loaded M3 model...");
		return countM3LOC(model);
	}
	
	// Count LOC for a location (e.g. method or file).
	// @param location: location to count lines for.
	// @return: number of linces of code for location.
	public int countLOC(loc location) {
		f = readFile(location);
		return countStringLOC(f);
	}
	
	// Does actual line counting on a string.
	// @param f: string of file or part of file to count LOC for.
	// @return: number of LOC for given string.
	public int countStringLOC(str f) {
		// Remove all tab characters.
		fTab = replaceAll(f, "\t", "");
				// Remove multiline comments.
		f = "";
		while(contains(fTab, "/*")) // Still some multiline comments.
		{
	        f += substring(fTab, 0, findFirst(fTab, "/*")); // Code before.
	        fTab = substring(fTab, findFirst(fTab,"/*") + 2, size(fTab)); // Code within + after. 
	        fTab = substring(fTab, findFirst(fTab,"*/") + 2, size(fTab)); // Code after.
    	}
    	// File now doesn't contain any multiline comments
		f += fTab;
		
		// Convert to list of lines
		list[str] lines = split("\n", f);
		
		// Trim lines
		lines = [trim(l) | l <- lines];
		
		// Only lines larger than 1 char, non-comment, and non-import
		list[str] newLines = [];
		for(line <- lines)
		{
			if(!isEmpty(line) && !startsWith(line, "//") && !startsWith(line, "import ")) newLines += line;
		}
		
		return size(newLines);
	}
	
		
		
		
	public list[tuple[loc, tuple[int,int],tuple[int,int], tuple[int,int], tuple[int,int], tuple[int,int]]] extractIfLocations (loc l){
	
	set[tuple[loc, tuple[int,int],tuple[int,int],tuple[int,int], tuple[int,int], tuple[int,int]]] list1 = {};
	set[loc] list2 = {};
	m = createM3FromEclipseProject(l);
	mthds = toList(methods(m));
	
	for (mtd <- mthds){
	methAST = getMethodASTEclipse(mtd, model=m);
	for (/Statement s <-methAST){
	//else if (\if(_,_) :=s){	//list1 = list1 + <giveLoc(e), st@src.begin, e@src.begin, e@src.end, s@src.begin>;
	////list2 = list2 + (st@src);}
	//}	
	if (\if(e,\block(ss)) :=s){	list1 = list1 + <giveLoc(e), ss[0]@src.begin, e@src.begin, e@src.end, s@src.begin, ss[size(ss)-1]@src.end>;
	list2 = list2 + (ss[0]@src);}	
	
	else if (\if(e,\block(ss),\block (els)) := s){
	list2 = list2 + (ss[0]@src);
	list2 = list2 + (els[0]@src);
	list1 = list1 + <giveLoc(e), ss[0]@src.begin, e@src.begin, e@src.end, s@src.begin, ss[size(ss)-1]@src.end>;
	list1 = list1 + <giveLoc(e), els[0]@src.begin, e@src.begin, e@src.end, s@src.begin, els[size(els)-1]@src.end>;
	}
	else if (\if(e,\block(stst),c) :=s){	list1 = list1 + <giveLoc(e), stst[0]@src.begin, e@src.begin, e@src.end, s@src.begin, stst[size(stst)-1]@src.end>;
	list1 = list1 + <giveLoc(e), c@src.begin, e@src.begin, e@src.end, s@src.begin, c@src.end>;
	list2 = list2 + (stst[0]@src);}//case}	
			
	
	else if (\if(e,st1,st2) :=s){	
	
	list1 = list1 + <giveLoc(e), st1@src.begin, e@src.begin, e@src.end, s@src.begin, st1@src.end>;
	list1 = list1 + <giveLoc(e), st2@src.begin, e@src.begin, e@src.end, s@src.begin, st2@src.end>;
	//list2 = list2 + (st@src);}
	}	
	}
	}
	return toList(list1);
	}
	
	
		public list[tuple[loc, tuple[int,int],tuple[int,int], tuple[int,int], tuple[int,int], tuple[int,int]]] extractIfLocationsOld (loc l){
	
	set[tuple[loc, tuple[int,int],tuple[int,int],tuple[int,int], tuple[int,int], tuple[int,int]]] list1 = {};
	set[loc] list2 = {};
	m = createM3FromEclipseProject(l);
	mthds = toList(methods(m));
	
	for (mtd <- mthds){
	
		methAST = getMethodASTEclipse(mtd, model=m);
	;
		for (/Statement s <-methAST){
			
	//		if (\if(_,_) :=s){	//list1 = list1 + <giveLoc(e), s@src.begin, s@src.begin, s@src.end, s@src.begin>;
	//		println(s@src);//println(s@src);
	//		//list2 = list2 + (s@src);}	
	//}
			if (\if(e,\block(ss)) :=s){	list1 = list1 + <giveLoc(e), ss[0]@src.begin, e@src.begin, e@src.end, s@src.begin, ss[size(ss)-1]@src.end>;
			//println(s@src);
			list2 = list2 + (ss[0]@src);	
	println(s@src);}
			else if (\if(e,\block(ss),\block (els)) := s){
			list2 = list2 + (ss[0]@src);
			list2 = list2 + (els[0]@src);
				list1 = list1 + <giveLoc(e), ss[0]@src.begin, e@src.begin, e@src.end, s@src.begin, ss[size(ss)-1]@src.end>;
				list1 = list1 + <giveLoc(e), els[0]@src.begin, e@src.begin, e@src.end, s@src.begin, els[size(els)-1]@src.end>;
	println(s@src);}
	
			else if (\if(e,\block(stst),c) :=s){	list1 = list1 + <giveLoc(e), stst[0]@src.begin, e@src.begin, e@src.end, s@src.begin, stst[size(stst)-1]@src.end>;
				list1 = list1 + <giveLoc(e), c@src.begin, e@src.begin, e@src.end, s@src.begin, c@src.end>;
			println(s@src);
			list2 = list2 + (stst[0]@src);}//case}	
			
			//else if (\if(_,_) :=s){	//list1 = list1 + <giveLoc(e), st@src.begin, e@src.begin, e@src.end, s@src.begin>;
			//println(st@src);
			//println(s@src);
			////list2 = list2 + (st@src);}
			//}	
	
	else if (\if(e,st1,st2) :=s){	
	
	list1 = list1 + <giveLoc(e), st1@src.begin, e@src.begin, e@src.end, s@src.begin, st1@src.end>;
	list1 = list1 + <giveLoc(e), st2@src.begin, e@src.begin, e@src.end, s@src.begin, st2@src.end>;
	
			//println(st@src);
			println(st1@src);
			println(st2@src);
			//list2 = list2 + (st@src);}
			}	
	}
	}
	for(el <- toList(list1)){
	println(el[4]);
	}
	return toList(list1);
	}


	public list[tuple[loc, tuple[int,int],tuple[int,int], tuple[int,int]]] extractIfLocations9 (loc l){
	
	set[tuple[loc, tuple[int,int],tuple[int,int],tuple[int,int]]] list1 = {};
	set[loc] list2 = {};
	m = createM3FromEclipseProject(l);
	mthds = toList(methods(m));
	
	for (mtd <- mthds){
	
		methAST = getMethodASTEclipse(mtd, model=m);
	
	//	for (/Statement s <-methAST){
	//
	//		if (\if(e,\block(ss)) :=s){	list1 = list1 + <giveLoc(e), ss[0]@src.begin, e@src.begin, e@src.end>;
	//		list2 = list2 + (ss[0]@src);}	
	//
	//		if (\if(e,\block(ss),\block (els)) := s){
	//		list2 = list2 + (ss[0]@src);
	//		list2 = list2 + (els[0]@src);
	//			list1 = list1 + <giveLoc(e), ss[0]@src.begin, e@src.begin, e@src.end>;
	//			list1 = list1 + <giveLoc(e), els[0]@src.begin, e@src.begin, e@src.end>;
	//}
	//
	//		if (\if(e,\block(stst),c) :=s){	list1 = list1 + <giveLoc(e), stst[0]@src.begin, e@src.begin, e@src.end>;
	//		list2 = list2 + (stst[0]@src);}//case}	
	//			
	//}
	}
	return toList(list1);
	}
	
	public tuple[lrel[int,int],lrel[int,int]] separateSingleFromMultipleIfs(list[tuple[int,int]] locations){
	ds = distribution([el[0]|el<-locations]); 
	lms = [m|m<-ds,ds[m]>1];
	x = [lst|lm <- lms,tuple[int x,int y] lst <- locations,lm==lst[0]];
	return <x,locations-x>;		
	}
		
	public str addEmptyLines(str x, lrel[int,int] l){
	fst = l[0];
	list[str] new = [x[0..fst[1]]];
	for (el <- [0..size(l)-1]){
	new = new + x[l[el][1]..l[el+1][1]];
	}
	new = new + x[l[size(l)-1][1]..];
	return intercalate("\n\n",new);
	}
					
	public list[lrel[int,int]] processMultipleIfs (lrel[int,int] mult){
	list[lrel[int,int]] lst = [];
	for (l<-mult){
	m=l;
	mult = drop(0,mult);
	x = ([s|s<-mult,s[0]==m[0]]);
	mult = mult - x;
	if (size(x)!=0){
	lst = lst + [x] ;}
	}
	return lst;
	}
	
	public list[str] insertBlankLines2(loc file2, list[tuple[int,int]] locations){
	
	separated = separateSingleFromMultipleIfs (locations);                            	                    //println("separated: <separated>");
	mltple = processMultipleIfs(separated[0]);
	ml = createM3FromEclipseProject(file2);
		//println("הגעתי");
		loc ln1 = getOneFrom(files(createM3FromEclipseProject(file2)));
		//list[str] lines = readFileLines(file2);
	list[str] lines = readFileLines(ln1);
	for (ln <- separated[1]){//locations[0]
	lines[ln[0]-1] = lines[ln[0]-1][0..ln[1]-1] + "\n\n" + lines[ln[0]-1][ln[1]..];
	}
	for (ln <- mltple){
	lines[ln[0][0]-1] = addEmptyLines(lines[ln[0][0]-1], ln);
	}
	//println("start");
	for(ln<-lines){
	//println(ln);
	;}
	return lines;
	}
		
		//public void doIt (loc lc, str s, tuple[int,int] c){
	public void doIt (loc lc){
	l = getProjectOfAFile(lc);
	//l = lc;
	printToFile2(l,(insertBlankLines2(l,[aa[1]|aa<-extractIfLocations(l)])));
	addPredicatePrinting(l);
	addExecutionFile(lc);
	}
	
	
	public void addPredicatePrinting(loc y){
	
	w = extractIfLocations(y);
	fls = {lss[0]|lss<-w};
	for(f<-fls){
	lins = readFileLines(f);
	for(l<-toSet(w)[f]){
	if (contains(lins[l[4][0]-1],"//")){
	x = replaceFirst(lins[l[4][0]-1],"//","}//");
	lins[l[4][0]-1] = x;
	}
	else {
	lins[l[4][0]-1] = lins[l[4][0]-1]+"}";
	}
	}
	for(l<-toSet(w)[f]){
	//lins[l[0][0]-1] = lins[l[0][0]-1][0..l[0][1]]+" { try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(\"testtext.txt\", true)))) {}catch (IOException ioException) {} \n" + lins[l[0][0]-1][l[0][1]..];
	lins[l[0][0]-1]=lins[l[0][0]-1][0..l[0][1]]+"{try(PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter(\"testtext.txt\",true)))){out.println(\"<l[1]>,<l[2]>,<f>,<l[0]>,<l[4]>,<l[3]>\");}catch(IOException ioException){} \n" + lins[l[0][0]-1][l[0][1]..];
	}
	lins[0] = 	"package experiment;\nimport java.io.BufferedWriter;\nimport java.io.FileWriter;\nimport java.io.IOException;\nimport java.io.PrintWriter;\n\n" + lins[0];
	fileName2 = /.*src\/<y:.*>/ := f.path ? y : "Error!";
	locStr = "src/experiment" + "/" + fileName2;
	loca = getProjectOfAFile(f)+locStr;	//printToFile3(lins, |file:///C:/Users/Klant/workspace5/predicate3/src/experiment/AssignmentTwo.java|);
		println("check: <loca>");
		printToFile3(lins, loca);
	}
	}
	
	//public void addPredicatePrinting3(loc y){
	public void addPredicatePrinting3(){
	
	y = |project://predicate1/src/experiment/AssignmentTwo.java|;
	//y = |project://predicate3/src/experiment/AssignmentTwo.java|;
	w = extractIfLocations(y);
	fls = {lss[0]|lss<-w};
	for(f<-fls){
	lins = readFileLines(f);
	//grs = <f,toList(toSet(x)[f])>;
	for(l<-toSet(w)[f]){
	lins[l[0][0]-1] = "try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(\"testtext.txt\", true)))) {out.println(\"<l[1]>,<l[2]>,<f>\");}catch (IOException ioException) {}\n" + lins[l[0][0]-1];//z[r[0][0]-1];
	}
	lins[2] = 	"package experiment;\nimport java.io.BufferedWriter;\nimport java.io.FileWriter;\nimport java.io.IOException;\nimport java.io.PrintWriter;\n\n" + lins[2];
	//lins = "packaget experiment" + lins;
	//printToFile3(lins, |file:///C:/Users/Klant/workspace5/predicate1/src/experiment/AssignmentTwo.java|);
	printToFile3(lins, y);
	}
	}
						

	
	public void doOnlyIt (loc l){
	printToFile2(l,(insertBlankLines2(l,[aa[1]|aa<-extractIfLocations(l)])));
	//addPredicatePrinting(l);
	}


	public void addPredicatePrintingOld(loc y){
	
	
	//y = |project://predicate1/src/experiment/AssignmentTwo.java|;
	w = extractIfLocations(y);
	println(w);
	fls = {lss[0]|lss<-w};
	println(fls);
	for(f<-fls){
	println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	lins = readFileLines(f);
	//grs = <f,toList(toSet(x)[f])>;
	println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	for(l<-toSet(w)[f]){
	//if (l[0][0]-1 != l[4][0]-1){
//lins[l[4][0]-1] = lins[l[4][0]-1][0..l[4][1]]+"}" + lins[l[4][0]-1][l[4][1]..];
lins[l[4][0]-1] = lins[l[4][0]-1]+"}";
println(1);
println(lins[l[4][0]-1]);
//println(lins[l[4][0]][0..l[4][1]]);
//println(lins[l[4][0]][l[4][1]..]);
//lins[l[4][0]] = lins[l[4][0]][0..l[4][1]]+"}" + lins[l[4][0]][0..l[4][1]];
//println(l);
//	println(l[4]);

	//lins[l[0][0]-1] = "{try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(\"testtext.txt\", true)))) {out.println(\"<l[1]>,<l[2]>,<f>,<l[3]>\");}catch (IOException e) {} \n" + lins[l[0][0]-1];//z[r[0][0]-1];
			//lins[l[0][0]-1] = "{try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(\"testtext.txt\", true)))) {out.println(\"<l[1]>,<l[2]>,<f>,<l[3]>\");}catch (IOException e) {} \n" + lins[l[0][0]-1];//z[r[0][0]-1];
		lins[l[0][0]-1] = lins[l[0][0]-1][0..l[0][1]]+" { try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(\"testtext.txt\", true)))) {out.println(\"<l[1]>,<l[2]>,<f>,<l[3]>\");}catch (IOException ioException) {} \n" + lins[l[0][0]-1][l[0][1]..];
		//println(lins[l[0][0]-1]);
		//"{" + lins[l[0][0]-1];//z[r[0][0]-1];
	//}
	//else 
	//lins[l[3][0]] = lins[l[3][0]][0..l[3][1]]+"}" + lins[l[3][0]][0..l[3][1]];
	//lins[l[0][0]-1] = "{try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(\"testtext.txt\", true)))) {out.println(\"<l[1]>,<l[2]>,<f>,<l[3]>\");}catch (IOException e) {}\n" + lins[l[0][0]-1];//z[r[0][0]-1];
	
	println("------------------------------------------------------");
	}
	//lins[2] = 	"package experiment;\nimport java.io.BufferedWriter;\nimport java.io.FileWriter;\nimport java.io.IOException;\nimport java.io.PrintWriter;\n\n" + lins[2];
	lins[0] = 	"package experiment;\nimport java.io.BufferedWriter;\nimport java.io.FileWriter;\nimport java.io.IOException;\nimport java.io.PrintWriter;\n\n" + lins[0];
	//lins = "packaget experiment" + lins;
	
	//printToFile3(lins, |file:///C:/Users/Klant/workspace5/predicate1/src/experiment/AssignmentTwo.java|);
	fileName2 = /.*src\/<y:.*>/ := f.path ? y : "Error!";
	locStr = "experiment" + "/" + fileName2;
	loca = getProjectOfAFile(f)+locStr;
	
	//writeFile(loca,lns88[0]);
	//for (ln<-lns88[1..]){
	//appendToFile(loca,"\n");
	//appendToFile(loca,ln);
	//}
	//
	//printToFile3(lins, |file:///C:/Users/Klant/workspace5/predicate3/src/experiment/AssignmentTwo.java|);
		printToFile3(lins, loca);
	//printToFile3(lins, y);
	}
	}
	
	//public void addPredicatePrinting3(loc y){
	public void addPredicatePrinting3(){
	
	y = |project://predicate1/src/experiment/AssignmentTwo.java|;
	//y = |project://predicate3/src/experiment/AssignmentTwo.java|;
	w = extractIfLocations(y);
	fls = {lss[0]|lss<-w};
	for(f<-fls){
	lins = readFileLines(f);
	//grs = <f,toList(toSet(x)[f])>;
	for(l<-toSet(w)[f]){
	lins[l[0][0]-1] = "try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(\"testtext.txt\", true)))) {out.println(\"<l[1]>,<l[2]>,<f>\");}catch (IOException ioException) {}\n" + lins[l[0][0]-1];//z[r[0][0]-1];
	}
	lins[2] = 	"package experiment;\nimport java.io.BufferedWriter;\nimport java.io.FileWriter;\nimport java.io.IOException;\nimport java.io.PrintWriter;\n\n" + lins[2];
	//lins = "packaget experiment" + lins;
	//printToFile3(lins, |file:///C:/Users/Klant/workspace5/predicate1/src/experiment/AssignmentTwo.java|);
	printToFile3(lins, y);
	}
	}
	
	//--------------------------------------------------------
	
	//takes a file, removes package declaration and adds new temporary one  
	public list[str] printest4(loc l,int i){
	
	 ast = createAstFromFile(l,true);
	 lns = readFileLines(l);
	 
	if (\compilationUnit(Declaration package, list[Declaration] imports, list[Declaration] types) := ast){
	
	for (n<-[0..1]){println("here: <n>");}
	endPackage = package@src.end;
	println(package@src);
	for(nmn<-[0..endPackage[0]-1]){
	lns[nmn] = "//"+lns[nmn];
	}
	lns[endPackage[0]-1] = lns[endPackage[0]-1][endPackage[1]..];
	pckg = /.*src\/<y:.*>\/.*.java/ := l.path ? replaceAll(y,"/",".") : "Error!";
	lns[0] = "package experiment" + toString(i) + "." + pckg + ";" + lns[0] +"\n";
	
	}
	return lns;
	}
	
	
	
	public void test1(){
	xx = [1,2,3,4,4,4,5];
	l = [];
	for(x<-xx){

	if (!(x in l)) {
		println(x);
		l = l + x;
	}
	else {l = delete(xx,indexOf(xx,x));
	l = l + x;}
}
		println(l);
	}


	
	public void aha (loc l){
	llls = reverse(readFileLines(l+"testtext.txt"));
	//llls = reverse(readFileLines(|file:///C:/Users/Klant/workspace5/predicate1/testtext.txt|));
	
	lx = [];
	println(l+"abc.txt");
	println(l+"testtext.txt");
	loc l2 = l + "abc.txt";
	writeFile(l2,llls[0]);
	for(x<-llls[1..]){
	if (!(x in lx)) {
		appendToFile(l2, "\n");
		appendToFile(l2, x);
		lx = lx + x;
	}
	}
	
	
	}
	public void abc (){
	
	loc f = |file:///C:/Users/Klant/workspace5/project164/src/experiment/triangulate.java|;
	lnns4 = readFileLines(f);
	loc lc = |file:///C:/Users/Klant/workspace5/project165/test1|;
	writeFile(lc,lnns4[0]);
	for (ln<-lnns4[1..]){
	appendToFile(lc,"\n");
	appendToFile(lc,ln);
	}
	
	
	}
	
	
		public void addExecutionFile(loc l){
	//public void abcdf (loc p, str m){
	
		//loc l = toLocation("file:///");//
	//str t2 = replaceAll(s, "/","\\");
	//println("-\> <ts>");
	list[str] lns = readFileLines(|file:///C:/Users/Klant/workspace5/ok/src/Execute.java|);
	
	fileName = /.*\/<y:.*>.java/ := l.path ? "experiment."+y : "error";
	
	projectLoc = /\/<x:.*><y:src\/.*>/ := l.path ? x : "Error!";
	println("projectL <projectLoc> src <fileName>");
	projectPath = replaceAll(projectLoc, "/","\\\\");
	str st = "\t\t\tString st = " + "\"" + projectPath + "bin " + fileName+"\";"; //locat = "C:\\Users\\Klant\\workspace5\\" + "\\bin " + fileName;
println(st);
	loc pl = toLocation("file:///"+projectLoc+"src/experiment/Execute.java");
	println("---\>: <pl>");
	writeFile(pl,"package experiment;");
	for (ln<-lns[1..12]){
	appendToFile(pl,"\n");
	appendToFile(pl,ln);
	}
	appendToFile(pl,"\n");
	appendToFile(pl,st);
		for (ln<-lns[13..]){
	appendToFile(pl,"\n");
	appendToFile(pl,ln);
	}
	
	}
	
	public void abcde (list[value] vs, loc l){
	
	writeFile(l,vs[0]);
	for (ln<-vs[1..]){
	appendToFile(l,"\n");
	appendToFile(l,ln);
	}
	
	
	}
	
	public void manageSwitching(loc l){
	
	println(l+"testtext.txt");
		//llls = reverse(readFileLines(|file:///C:/Users/Klant/workspace5/project104/testtext.txt|));
	llls = reverse(readFileLines(l+"testtext.txt"));
	//llls = reverse(readFileLines(|file:///C:/Users/Klant/workspace5/predicate1/testtext.txt|));
	
	lx = [];
	for(x<-llls){

	if (!(x in lx)) {
		lx = lx + x;
	}
	//else {//l = delete(lx,indexOf(lx,x));
	//lx = delete(lx,indexOf(lx,x)) + x;}
	//println();
	
}
		println(lx);
	
		s = size(lx);
	//s = size(ls);
	//sts = /<y:.*\|><x:.*>\||/ := ls[0] ? x : "Error!";
	sts = /<y:.*\|><x:.*>\||/ := lx[0] ? x : "Error!";
	fls = giveProjectfiles(getProjectOfAFile(toLocation(sts)));
	println("ffff");
	println(fls);
	for (i<-[0..(s)]){
	sts3 = /<y:.*\|><x:.*>\||/ := lx[0] ? x : "Error!";
	//sts3 = /<y:.*\|><x:.*>\||/ := ls[0] ? x : "Error!";
	lnns4 = readFileLines(toLocation(sts3));
	
	println("hereee");
	for(fl<-fls){
	
	list[str] lns88 = printest4(fl,i);
	
	fileName2 = /.*src\/<y:.*>/ := fl.path ? y : "Error!";
	locStr = "experiment" + toString(i) + "/" + fileName2;
	loca = getProjectOfAFile(fl)+locStr;
	println("loca");
	writeFile(loca,lns88[0]);
	for (ln<-lns88[1..]){
	appendToFile(loca,"\n");
	appendToFile(loca,ln);
	}
	
	}
	
	//sts5 = /<y:.*\|><x:.*>\|/ := ls[i] ? x : "Error!";
	sts5 = /<y:.*\|><x:.*>\|/ := lx[i] ? x : "Error!";
	println(sts5);
	println(lx);
	lnns = printest4(toLocation(sts5),i);
	
	//println(split(",",substring(lx[i],1,5)));
	//println(substring(lx[i],1,5));
	//coordinates1 = <toInt(split(",",substring(ls[i],1,5))[0]),toInt(split(",",substring(ls[i],1,5))[1])>;
	//coordinates2 = <toInt(split(",",substring(ls[i],8,13))[0]),toInt(split(",",substring(ls[i],8,13))[1])>;
		
		
		str sstr1 = /\<<x:.*><z:\>.*\>>,<y:.*>/ := lx[i] ? x : "Error!";
		//str sstr2 = /\<<x:.*\<><z:.*><y:\>.*>/ := lx[i] ? z : "Error!";
		str sstr2 = /\<<x:.*\<><z:.*><y:\>,\|.*>/ := lx[i] ? z : "Error!";
str sstr3 = /<z:.*\|,\<><y:.*>\>/ := lx[i] ? y : "Error!";
coordinates1 = <toInt(split(",",sstr1)[0]),toInt(split(",",sstr1)[1])>;
coordinates2 = <toInt(split(",",sstr2)[0]),toInt(split(",",sstr2)[1])>;
coordinates3 = <toInt(split(",",sstr3)[0]),toInt(split(",",sstr3)[1])>;

	
	//	coordinates1 = <toInt(split(",",substring(lx[i],1,5))[0]),toInt(split(",",substring(lx[i],1,5))[1])>;
	//coordinates2 = <toInt(split(",",substring(lx[i],8,13))[0]),toInt(split(",",substring(lx[i],8,13))[1])>;
	
	
	fileName2 = /.*src\/<y:.*>/ := sts5 ? y : "Error!";
	locStr = "experiment" + toString(i) + "/" + fileName2;
	loca2 = getProjectOfAFile(toLocation(sts5))+locStr;
	
	
	
//lins[l[0][0]-1] = "try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(\"testtext.txt\", true)))) {out.println(\"<l[1]>,<l[2]>,<f>\");}catch (IOException e) {}\n" + lins[l[0][0]-1];//z[r[0][0]-1];
	lnns[coordinates2[0]-1] = lnns[coordinates2[0]-1][0..coordinates2[1]]+")" + lnns[coordinates2[0]-1][coordinates2[1]..];
	lnns[coordinates1[0]-1] = lnns[coordinates1[0]-1][0..coordinates1[1]]+"!(" + lnns[coordinates1[0]-1][coordinates1[1]..];
	
	lnns[coordinates3[0]-1] = lnns[coordinates3[0]-1][0..coordinates3[1]] + "try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(\"results.txt\", true)))) {out.println(\"<i>, \");}catch (IOException ioException) {}\n" + lnns[coordinates3[0]-1][coordinates3[1]..];
	println("loca2: <loca2>");
	println(lnns[0]);
	lnns[0] = 	"package experiment<i>;\nimport java.io.BufferedWriter;\nimport java.io.FileWriter;\nimport java.io.IOException;\nimport java.io.PrintWriter;\n\n" + lnns[0];
	
		writeFile(loca2,lnns[0]);
	
	for (ln<-lnns[1..]){
	appendToFile(loca2,ln);
	appendToFile(loca2,"\n");
	}
	
	}
	
	
	//addRestOfTheFiles(|project://yyyyy|){for(f<-files(m)){>writeFile((|file://...|+experiment),lns[0]+packacge/experiment);
	}
	
	
	public void manageSwitching4(loc l){
	
	println(l+"abc.txt");
	llls = reverse(readFileLines(l+"abc.txt"));
	
		s = size(llls);
	sts = /<y:.*\|><x:.*>\||/ := llls[0] ? x : "Error!";
	fls = giveProjectfiles(getProjectOfAFile(toLocation(sts)));
	for (i<-[0..(s)]){
	sts3 = /<y:.*\|><x:.*>\||/ := llls[0] ? x : "Error!";
	lnns4 = readFileLines(toLocation(sts3));
	
	for(fl<-fls){
	
	list[str] lns88 = printest4(fl,i);
	
	fileName2 = /.*src\/<y:.*>/ := fl.path ? y : "Error!";
	locStr = "experiment" + toString(i) + "/" + fileName2;
	loca = getProjectOfAFile(fl)+locStr;
	println("loca");
	writeFile(loca,lns88[0]);
	for (ln<-lns88[1..]){
	appendToFile(loca,"\n");
	appendToFile(loca,ln);
	}
	
	}
	
	sts5 = /<y:.*\|><x:.*>\|/ := llls[i] ? x : "Error!";
	lnns = printest4(toLocation(sts5),i);
	
		str sstr1 = /\<<x:.*><z:\>.*\>>,<y:.*>/ := llls[i] ? x : "Error!";
		str sstr2 = /\<<x:.*\<><z:.*><y:\>,\|.*>/ := llls[i] ? z : "Error!";
str sstr3 = /<z:.*\|,\<><y:.*>\>/ := llls[i] ? y : "Error!";
coordinates1 = <toInt(split(",",sstr1)[0]),toInt(split(",",sstr1)[1])>;
coordinates2 = <toInt(split(",",sstr2)[0]),toInt(split(",",sstr2)[1])>;
coordinates3 = <toInt(split(",",sstr3)[0]),toInt(split(",",sstr3)[1])>;
	
	fileName2 = /.*src\/<y:.*>/ := sts5 ? y : "Error!";
	locStr = "experiment" + toString(i) + "/" + fileName2;
	loca2 = getProjectOfAFile(toLocation(sts5))+locStr;
	
	
	lnns[coordinates2[0]-1] = lnns[coordinates2[0]-1][0..coordinates2[1]]+")" + lnns[coordinates2[0]-1][coordinates2[1]..];
	lnns[coordinates1[0]-1] = lnns[coordinates1[0]-1][0..coordinates1[1]]+"!(" + lnns[coordinates1[0]-1][coordinates1[1]..];
	lnns[coordinates3[0]-1] = lnns[coordinates3[0]-1][0..coordinates3[1]] + "try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(\"results.txt\", true)))) {out.println(\"<i>, \");}catch (IOException ioException) {}\n" + lnns[coordinates3[0]-1][coordinates3[1]..];
	lnns[0] = 	"package experiment<i>;\nimport java.io.BufferedWriter;\nimport java.io.FileWriter;\nimport java.io.IOException;\nimport java.io.PrintWriter;\n\n" + lnns[0];
	
		writeFile(loca2,lnns[0]);
	
	for (ln<-lnns[1..]){
	appendToFile(loca2,ln);
	appendToFile(loca2,"\n");
	}
	}
	
	}
	
	public void manag(int k,int j) {
	
	l = manageSwitching6(|project://project165|);
	println(size(l));
	ll = |file:///C:/|;//|project://project165|;
//for (i<-[0..size(l)]){
	for (i<-[k..j]){
	println(i);
//|file:///C:/Users/Klant/workspace5/project165/src/experiment<i>.java|
	println(l[i][1]);
	tels = "ab/abc<i>.java";
	lcl = ll 	+ tels;
	// "file:///C:/Users/Klant/workspace5/" + projectName + "/src/experiment<i>.java";
	st = "file:///C:/Users/Klant/workspace5/project4/src/experiment<i>/LinkedList.java";
	xl = toLocation(st);
	println(lcl);
	//writeFile(lcl, "!");
	//abcde(l[i][0],l[i][1]);
	abcde(l[i][0],xl);
	//abc();
	}
	
	}



//public void doIt (loc lc, str s, tuple[int,int] c){
	public void manag2(loc lc, str s, tuple[int,int] c) {
	fileName = /.*\/<y:.*.java>/ := lc.path ? y : "error";
	println("=============\>: <fileName>");
	projectPat = /\/<x:.*\/><y:src\/.*>/ := lc.path ? x : "Error!";
	println("ProjectPATH: "+projectPat);
lct = getProjectOfAFile(lc);	
	println("lct: <lct>");
	aha(lct);
	println("lct: <lct>");
		l = manageSwitching6(lc,s,c);

	ll = |file:///C:/|;
	for (i<-[0..size(l)]){
	st = "file:///"+projectPat+"src/experiment<i>/"+fileName;	//xl = lct + "src/experiment<i>/<m>.java";  
	xl = toLocation(st);
	println(xl);
	abcde(l[i][0],xl);
	}
	
	}

public void tesit(loc l){
path = giveProjectOfAFile(l);
ls = readFileLines(path+"results.txt");
vs = [contains(x,"true")|x<-ls];
if (!(contains(vs,true))) 
println("The tool did not seem to find any critical predicate");
else if ([p|p<-l[indexOf(true,vs)..],p==false]==[])
present(index(l,indexOf(true,vs)));
}

	
public void present(loc l, int i){

llls = reverse(readFileLines(l+	"abc.txt"));
str sstr4 = /<z:.*\|,\<><y:.*><x:\>,\<.*\>,\<.*>\>/ := llls[i] ? y : "Error!";
str sstr5 = /<z:.*\|,\<.*\>,\<><y:.*><x:\>,\<.*>\>/ := llls[i] ? y : "Error!";
coordinates4 = <toInt(split(",",sstr3)[0]),toInt(split(",",sstr4)[1])>;
coordinates5 = <toInt(split(",",sstr3)[0]),toInt(split(",",sstr5)[1])>;
str sstr3 = /.*\|<c:.*>\|.*/ := llls[i] ? c : "A";
loc l = toLocation(sstr3);
q = readFileLines(l);

println(q[coordinates4[0]-1][coordinates4[1]..]);
for(j<-[coordinates4[0]..coordinates5[0]-2]){
println(q[j]);
}
println(q[coordinates5[0]-1][..coordinates5[1]]);
}
	
	public list[tuple[list[value],loc]] manageSwitching6(loc l, str as, tuple[int,int] coor){
	
	
	list[tuple[list[value],loc]] lineas = [];
		lct = getProjectOfAFile(l);
		llls = reverse(readFileLines(lct+	"abc.txt"));	
		println("llls " +llls);
		s = size(llls);
	sts = /<y:.*\|><x:.*>\||/ := llls[0] ? x : "Error!";
	println("sts" + sts);
	fls = giveProjectfiles(getProjectOfAFile(toLocation(sts)));
	println("fls"+fls);
	for (i<-[0..(s)]){
	
	sts5 = /<y:.*\|><x:.*>\|/ := llls[i] ? x : "Error!";
	lnns = printest4(toLocation(sts5),i);
//	
		//str sstr1 = /\<<x:.*><z:\>.*\>>,<y:.*>/ := llls[i] ? x : "Error!";
		str sstr1 = /\<<x:.*><z:\>.*\>>,<y:\|.*>/ := llls[i] ? x : "Error!";
		str sstr2 = /\<<x:.*\<><z:.*><y:\>,\|.*>/ := llls[i] ? z : "Error!";
str sstr3 = /<z:.*\|,\<.*,\<><y:.*>\>/ := llls[i] ? y : "Error!";
//str sstr3 = /<z:.*\|,\<><y:.*>\>/ := llls[i] ? y : "Error!";
coordinates1 = <toInt(split(",",sstr1)[0]),toInt(split(",",sstr1)[1])>;
coordinates2 = <toInt(split(",",sstr2)[0]),toInt(split(",",sstr2)[1])>;
coordinates3 = <toInt(split(",",sstr3)[0]),toInt(split(",",sstr3)[1])>;

str sstr4 = /<z:.*\|,\<><y:.*><x:\>,\<.*\>,\<.*>\>/ := llls[i] ? y : "Error!";
str sstr5 = /<z:.*\|,\<.*\>,\<><y:.*><x:\>,\<.*>\>/ := llls[i] ? y : "Error!";
coordinates4 = <toInt(split(",",sstr3)[0]),toInt(split(",",sstr4)[1])>;
coordinates5 = <toInt(split(",",sstr3)[0]),toInt(split(",",sstr5)[1])>;
	
	fileName2 = /.*src\/<y:.*>/ := sts5 ? y : "Error!";
	locStr = "experiment" + toString(i) + "/" + fileName2;
	loca2 = getProjectOfAFile(toLocation(sts5))+locStr;
//	
//	
	lnns[coordinates2[0]-1] = lnns[coordinates2[0]-1][0..coordinates2[1]]+")" + lnns[coordinates2[0]-1][coordinates2[1]..];
	lnns[coordinates1[0]-1] = lnns[coordinates1[0]-1][0..coordinates1[1]]+"!(" + lnns[coordinates1[0]-1][coordinates1[1]..];
	//lnns[coordinates3[0]-1] = lnns[coordinates3[0]-1][0..coordinates3[1]] + "try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(\"results.txt\", true)))) {out.println(\"<i>, \");}catch (IOException ioException) {}\n" + lnns[coordinates3[0]-1][coordinates3[1]..];
	lnns[0] = 	"package experiment<i>;\nimport java.io.BufferedWriter;\nimport java.io.FileWriter;\nimport java.io.IOException;\nimport java.io.PrintWriter;\n\n" + lnns[0];
//	
//	
	//println(coor[1]);
	lnns[coor[0]-1] = lnns[coor[0]-1][0..coor[1]] 
//"	{try(PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter(\"results.txt\",true)))){out.println(\"<97,13>,<97,23>,|file:///C:/Users/Klant/workspace5/project1/src/AssignmentTwo.java|,<97,9>\");}catch(IOException ioException){};";
	+ "try(PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter(\"results.txt\",true)))){out.println(\"<as>\");}catch(IOException ioException){} \n"
	+ lnns[coor[0]-1][coor[1]..];
	
	lineas = lineas + [<lnns,loca2>];
	}
	return lineas;
	}
	
	
	
	public void manageSwitching5(loc l){
	
	
	list[list[value]] lineas = [];
	llls = reverse(readFileLines(l+"abc.txt"));
	
		s = size(llls);
	sts = /<y:.*\|><x:.*>\||/ := llls[0] ? x : "Error!";
	fls = giveProjectfiles(getProjectOfAFile(toLocation(sts)));
	println(fls);
	for (i<-[0..(s)]){
	
	for(fl<-fls){
	//list[str] lns88 = readFileLines(fl);
	
	//list[str] lns88 = printest4(fl,i);
	//
	//fileName2 = /.*src\/<y:.*>/ := fl.path ? y : "Error!";
	//locStr = "experiment" + toString(i) + "/" + fileName2;
	//loca = getProjectOfAFile(fl)+locStr;
	abc();
	//writeFile(loca,lns88[0]);
	//for (ln<-lns88[1..]){
	//appendToFile(loca,"\n");
	//appendToFile(loca,ln);
	//}
	//
	}
	
	sts5 = /<y:.*\|><x:.*>\|/ := llls[i] ? x : "Error!";
	lnns = printest4(toLocation(sts5),i);
	
		str sstr1 = /\<<x:.*><z:\>.*\>>,<y:.*>/ := llls[i] ? x : "Error!";
		str sstr2 = /\<<x:.*\<><z:.*><y:\>,\|.*>/ := llls[i] ? z : "Error!";
str sstr3 = /<z:.*\|,\<><y:.*>\>/ := llls[i] ? y : "Error!";
coordinates1 = <toInt(split(",",sstr1)[0]),toInt(split(",",sstr1)[1])>;
coordinates2 = <toInt(split(",",sstr2)[0]),toInt(split(",",sstr2)[1])>;
coordinates3 = <toInt(split(",",sstr3)[0]),toInt(split(",",sstr3)[1])>;
	
	fileName2 = /.*src\/<y:.*>/ := sts5 ? y : "Error!";
	locStr = "experiment" + toString(i) + "/" + fileName2;
	loca2 = getProjectOfAFile(toLocation(sts5))+locStr;
	
	
	lnns[coordinates2[0]-1] = lnns[coordinates2[0]-1][0..coordinates2[1]]+")" + lnns[coordinates2[0]-1][coordinates2[1]..];
	lnns[coordinates1[0]-1] = lnns[coordinates1[0]-1][0..coordinates1[1]]+"!(" + lnns[coordinates1[0]-1][coordinates1[1]..];
	lnns[coordinates3[0]-1] = lnns[coordinates3[0]-1][0..coordinates3[1]] + "try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(\"results.txt\", true)))) {out.println(\"<i>, \");}catch (IOException ioException) {}\n" + lnns[coordinates3[0]-1][coordinates3[1]..];
	lnns[0] = 	"package experiment<i>;\nimport java.io.BufferedWriter;\nimport java.io.FileWriter;\nimport java.io.IOException;\nimport java.io.PrintWriter;\n\n" + lnns[0];
	
	
	lineas = lineas + [lnns];
	println("added 1");
	//abc();
	//println(lin);
		writeFile(loca2,lnns[0]);
	//
	for (ln<-lnns[1..]){
	appendToFile(loca2,ln);
	appendToFile(loca2,"\n");
	}
	}
	println(lineas);
	}
	
	
	
	rel[int, int] invert(rel[int,int] R){
   return {<Y, X> | <int X, int Y> <- R };
}

//@javaClass{org.rascalmpl.library.Prelude}
//public java int size(list[&T] lst);
//
//@javaClass{snippet.Snippet}
//public java void doThis();

//@javaClass{HelloWorld}
//public java void doThis();

//{

//System.out.println("Ok!!");
//}




	public void manageSwitching2(loc l){
	
	//|project://project103/|
		llls = reverse(readFileLines(l+"testtext.txt"));
	//llls = reverse(readFileLines(|file:///C:/Users/Klant/workspace5/project104/testtext.txt|));
	//llls = reverse(readFileLines(|file:///C:/Users/Klant/workspace5/predicate1/testtext.txt|));
	
	lx = [];
	for(x<-llls){

	if (!(x in lx)) {
		lx = lx + x;
	}
	//else {//l = delete(lx,indexOf(lx,x));
	//lx = delete(lx,indexOf(lx,x)) + x;}
	println(lx);
}
		
	
		s = size(lx);
	//s = size(ls);
	//sts = /<y:.*\|><x:.*>\||/ := ls[0] ? x : "Error!";
	sts = /<y:.*\|><x:.*>\||/ := lx[0] ? x : "Error!";
	fls = giveProjectfiles(getProjectOfAFile(toLocation(sts)));
	println("ffff");
	println(fls);
	for (i<-[0..(s-1)]){
	sts3 = /<y:.*\|><x:.*>\||/ := lx[0] ? x : "Error!";
	//sts3 = /<y:.*\|><x:.*>\||/ := ls[0] ? x : "Error!";
	lnns4 = readFileLines(toLocation(sts3));
	
	println("hereee");
	for(fl<-fls){
	
	list[str] lns88 = printest4(fl,i);
	
	fileName2 = /.*src\/<y:.*>/ := fl.path ? y : "Error!";
	locStr = "experiment" + toString(i) + "/" + fileName2;
	loca = getProjectOfAFile(fl)+locStr;
	
	writeFile(loca,lns88[0]);
	for (ln<-lns88[1..]){
	appendToFile(loca,"\n");
	appendToFile(loca,ln);
	}
	
	}
	
	sts5 = /<y:.*\|><x:.*>\|/ := ls[i] ? x : "Error!";
	
	lnns = printest4(toLocation(sts5),i);
	
	coordinates1 = <toInt(split(",",substring(ls[i],1,5))[0]),toInt(split(",",substring(ls[i],1,5))[1])>;
	coordinates2 = <toInt(split(",",substring(ls[i],8,13))[0]),toInt(split(",",substring(ls[i],8,13))[1])>;
	
	
	fileName2 = /.*src\/<y:.*>/ := sts5 ? y : "Error!";
	locStr = "experiment" + toString(i) + "/" + fileName2;
	loca2 = getProjectOfAFile(toLocation(sts5))+locStr;
	
	
	
	writeFile(loca2,lnns[0]);
	//lins[l[0][0]-1] = "try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(\"testtext.txt\", true)))) {out.println(\"<l[1]>,<l[2]>,<f>\");}catch (IOException e) {}\n" + lins[l[0][0]-1];//z[r[0][0]-1];
	//lnns[l[0][0]-1] = "try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(\"testtext.txt\", true)))) {out.println(\"<l[1]>,<l[2]>,<f>\");}catch (IOException e) {}\n" + lns[l[0][0]-1];//z[r[0][0]-1];
	lnns[coordinates2[0]-1] = lnns[coordinates2[0]-1][0..coordinates2[1]]+")" + lnns[coordinates2[0]-1][coordinates2[1]..];
	lnns[coordinates1[0]-1] = lnns[coordinates1[0]-1][0..coordinates1[1]]+"!(" + lnns[coordinates1[0]-1][coordinates1[1]..];
	for (ln<-lnns[1..]){
	appendToFile(loca2,ln);
	appendToFile(loca2,"\n");
	}
	
	}
	
	
	//addRestOfTheFiles(|project://yyyyy|){for(f<-files(m)){>writeFile((|file://...|+experiment),lns[0]+packacge/experiment);
	}
	
	
	//----------------------------------------------------------------------------
	
	public void printToFile2(loc lo, list[str] lns){
	loc ln1 = getOneFrom(files(createM3FromEclipseProject(lo)));
	
	fileName2 = /.*src\/<y:.*>/ := ln1.path ? y : "Error!!";
		locStr = "src/experiment" + "/" + fileName2;
	//loca = getProjectOfAFile(f)+locStr;
	loca = lo+locStr;
	
	//ltx = /<y:.*\/><x:.*\/src>\/<z:.*.java>/ :=  ? z : "Error!";
	writeFile(loca,"package experiment;");
	
	for (line <- lns[0..]){
	appendToFile(loca,line);
	appendToFile(loca,"\n");
	}
	//println(ln1.path);
	//println(fileName2);
	//println(loca);
	}
	
	//	public void printToFiles(list[str] lns){
	//writeFile(|project://predicate3/src/experiment/AssignmentTwo.java|,"package experiment;");
	//
	//for (line <- lns[0..]){
	//appendToFile(|project://predicate3/src/experiment/AssignmentTwo.java|,line);
	//appendToFile(|project://predicate3/src/experiment/AssignmentTwo.java|,"\n");
	//}
	//}
	//
	public set[loc] giveProjectfiles(loc l){
	m = createM3FromEclipseProject(l);		
	return files(m);
	 //return ast;
	}
	
	
	public loc getProjectOfAFile(loc l){
	ltx = /<y:.*\/><x:.*\/>src\/.*.java/ := l.path ? x : "Error!";
	return (toLocation("project://"+ltx));
	}
	
	public void printToFile3(list[str] lns, loc l){
		writeFile(l, lns[0]);
	for (line <- lns[1..]){
					appendToFile(l, "\n");
	appendToFile(l, line);
					}
	}
	
	public loc giveLoc(Expression e){
		loc location1 = toLocation(e@src.path);
		location1.scheme = "file";
		return location1;
	}
		
	
	public loc getProjectOfAFile2(loc l){
	ltx = /<y:.*\/><x:.*\/>src\/.*.java/ := l.path ? x : "Error!";
	println(ltx);
	return (toLocation("project://"));//+ltx));
	}