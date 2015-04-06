define prime as: Integer attribute in [1,3,5,7,11,13] 

define print as: native method receiving: Text value doing:
	Java: System.out.print(value);
	C#: System.Console.Write(value);
	Python2: print(objects=value,end="")
	Python3: print(objects=value,end="")
	JavaScript: process.stdout.write(value);

define printPrime as: method receiving: prime doing:
	print "/prime=" + prime

define main as: method receiving: Text{} options doing:
	printPrime 13
	switch on error doing:
		printPrime 14
	when any:
		print "/ok"
		
