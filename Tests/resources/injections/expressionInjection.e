define print as: native method receiving: Text value doing:
	Java: System.out.print(value);
	C#: System.Console.Write(value);
	Python2: print(objects=value,end="")
	Python3: print(objects=value,end="")
	JavaScript: process.stdout.write(value);

define test as: method receiving: Code code doing:
	a = 3
	b = 2
	s = "a+b=" + execute: code
	print s

define main as: method receiving: Text{} options doing:
	c = 1
	test with Code: a + b as code
	
