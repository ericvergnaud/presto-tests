define proto as: abstract method receiving: Text prefix returning: Text

define print as: native method receiving: Text value doing:
	Java: System.out.print(value);
	C#: System.Console.Write(value);
	Python2: print(objects=value,end="")
	Python3: print(objects=value,end="")
	JavaScript: process.stdout.write(value);

define printMethod as: method receiving: proto doing:
	s = proto with "prefix:" as prefix
	print s

define parentTest as: method doing:
	x = 150
	define subTest as: method receiving: Text prefix doing:
		return prefix + x
	return Method: subTest
	
define main as: method receiving: Text{} options doing:
	printMethod parentTest
