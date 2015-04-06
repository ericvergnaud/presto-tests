define print as: native method receiving: Text value doing:
	Java: System.out.print(value);
	C#: System.Console.Write(value);
	Python2: print(objects=value,end="")
	Python3: print(objects=value,end="")
	JavaScript: process.stdout.write(value);

define main as: method receiving: Text{} options doing:
	doc = Document
	doc.vals = [Document,Document,Document]
	doc.vals[2].text = "hello"
	print "doc.vals[2].text=" + doc.vals[2].text
	
