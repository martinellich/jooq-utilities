package io.seventytwo.jooq;

import org.jooq.codegen.GeneratorStrategy;
import org.jooq.codegen.JavaGenerator;
import org.jooq.codegen.JavaWriter;
import org.jooq.meta.ColumnDefinition;
import org.jooq.meta.TableDefinition;

/**
 * {@link JavaGenerator} implementation that adds equals() and hashCode() to a {@link org.jooq.Record}
 */
public class EqualsAndHashCodeJavaGenerator extends JavaGenerator {

    /**
     * If a table has a PrimaryKey, equals() and hashCode are generated based on that PrimaryKey
     *
     * @param table {@link TableDefinition}
     * @param out   {@link JavaWriter}
     */
    @Override
    protected void generateRecordClassFooter(TableDefinition table, JavaWriter out) {
        if (table.getPrimaryKey() != null) {

            String javaClassName = getStrategy().getJavaClassName(table, GeneratorStrategy.Mode.RECORD);

            out.println();

            out.tab(1).println("public boolean equals(Object o) {");
            out.tab(2).println("if (this == o) return true;");
            out.tab(2).println("if (o == null || getClass() != o.getClass()) return false;");
            out.println();
            out.tab(2).println(javaClassName + " record = (" + javaClassName + ") o;");
            out.println();
            for (ColumnDefinition columnDefinition : table.getPrimaryKey().getKeyColumns()) {
                String javaGetterName = getStrategy().getJavaGetterName(columnDefinition);

                out.tab(2).println("if (!java.util.Objects.equals(" + javaGetterName + "(), record." + javaGetterName + "())) return false;");
            }
            out.println();
            out.tab(2).println("return true;");
            out.tab(1).println("}");

            out.println();

            out.tab(1).println("public int hashCode() {");
            boolean first = true;
            StringBuilder sb = new StringBuilder();
            for (ColumnDefinition columnDefinition : table.getPrimaryKey().getKeyColumns()) {
                if (!first) {
                    sb.append(" * ");
                }
                String javaGetterName = getStrategy().getJavaGetterName(columnDefinition);
                sb.append("java.util.Objects.hashCode(").append(javaGetterName).append("())");
                first = false;
            }
            out.tab(2).println("return " + sb.toString() + ";");
            out.tab(1).println("}");
        }
    }

}
