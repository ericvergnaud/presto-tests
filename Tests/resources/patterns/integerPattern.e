define even as: Integer attribute matching value%2=0 

define print as: native method receiving: Text value doing:
	Java: System.out.print(value);
	C#: System.Console.Write(value);
	Python2: print(objects=value,end="")
	Python3: print(objects=value,end="")
	JavaScript: process.stdout.write(value);

define printEven as: method receiving: even doing:
	print "/even=" + even

define main as: method receiving: Text{} options doing:
	printEven 256
	switch on error doing:
		printEven 257
	when any:
		print "/ok"
