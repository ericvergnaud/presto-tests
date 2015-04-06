for folder in */;
do
  echo $folder;
  cd $folder;
	for file in *.e; do
    	mv "$file" "${file%.e}.pec" 
	done;
	for file in *.o; do
    	mv "$file" "${file%.o}.poc" 
	done;
	for file in *.p; do
    	mv "$file" "${file%.p}.psc" 
	done;
  cd ..;
done;
