define positiveInteger as: Integer attribute in [0..MAX_INTEGER] 

define print as: native method receiving: Text value doing:
	Java: System.out.print(value);
	C#: System.Console.Write(value);
	Python2: print(objects=value,end="")
	Python3: print(objects=value,end="")
	JavaScript: process.stdout.write(value);

define printPos as: method receiving: positiveInteger doing:
	print "/pos=" + positiveInteger

define main as: method receiving: Text{} options doing:
	printPos 42
	switch on error doing:
		printPos -7
	when any:
		print "/ok"
	
