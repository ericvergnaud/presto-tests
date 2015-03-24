define id as: Integer attribute 
define text as: Text attribute

define Entity as: enumerated category with attributes: id and text, and symbols:
	ENTITY_1 with 1 as id and "n1" as text
	ENTITY_2 with 2 as id and "n2" as text

define print as: native method receiving: Text value doing:
	Java: System.out.print(value);
	C#: System.Console.Write(value);
	Python2: print(objects=value,end="")
	Python3: print(objects=value,end="")
	JavaScript: process.stdout.write(value);

define main as: method receiving: Text{} options doing:
	print "/a=" + ENTITY_1.text
	print "/b=" + ENTITY_2.text
	
	
