
KOTLINC=kotlinc
JAVA=java
TARGETS=array_basic.jar collection_lazy.jar delegate_prop.jar extension_p.jar interface_basic.jar op_overload.jar \
class_basic.jar collection_overload.jar delegation.jar for_loop.jar is_as.jar method_override.jar \
scope2.jar collection_allany.jar collections.jar destruct.jar func_basic.jar lambda.jar nested_class.jar \
sealed_class.jar collection_filtermap.jar compare.jar exception.jar generic.jar lambda_sam.jar null_safety.jar \
string.jar collection_flat.jar constructor.jar extension_m.jar getset.jar lambda_with.jar object.jar when.jar

OPT=-include-runtime

all: $(TARGETS)

.PHONY : clean test
clean:
	rm -f *.jar

%.jar : %.kt
	$(KOTLINC) $(OPT) $< -d $@
	$(JAVA) -jar $@
