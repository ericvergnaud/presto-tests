define id as: Integer attribute 
define name as: Text attribute

define Entity as: category with attributes: id and name
define Person as: Entity
define Company as: Entity

define print as: native method receiving: Text value doing:
	Java: System.out.print(value);
	C#: System.Console.Write(value);
	Python2: print(objects=value,end="")
	Python3: print(objects=value,end="")
	JavaScript: process.stdout.write(value);

define printName as: method receiving: Person p doing:
	print with "/person=" + p.name as value

define printName as: method receiving: Company c doing:
	print with "/company=" + c.name as value

define printName as: method receiving: Entity e doing:
	print with "/entity=" + e.name as value

define printEntity as: method receiving: Entity e doing:
	printName e
	
define main as: method receiving: Text{} options doing:
	c = Company with 1 as id and "IBM" as name
	printEntity c
	p = Person with 1 as id and "James" as name
	printEntity p
