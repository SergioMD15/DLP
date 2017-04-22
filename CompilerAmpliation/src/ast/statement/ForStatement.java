package ast.statement;

import java.util.List;

import ast.expression.Expression;
import ast.expression.Incrementable;
import visitor.Visitor;

public class ForStatement implements Statement {

    public int line, column;
    public Expression condition;
    public Statement initialize;
    public Incrementable increment;
    public List<Statement> body;
    
    public ForStatement(int line, int column, Statement first, Expression second, Incrementable third,
	    List<Statement> body) {
	this.line = line;
	this.column = column;
	this.initialize = first;
	this.condition = second;
	this.increment = third;
	this.body = body;
    }

    @Override
    public int getLine() {
	return this.line;
    }

    @Override
    public int getColumn() {
	return this.column;
    }

    @Override
    public Object accept(Visitor v, Object params) {
	v.visit(this, params);
	return null;
    }

    public Statement getInitilization() {
	return this.initialize;
    }
    
    public Expression getCondition() {
	return this.condition;
    }
    
    public Incrementable getIncrement() {
	return this.increment;
    }
    
    public List<Statement> getBody() {
	return this.body;
    }
    
    @Override
    public String toString(){
	StringBuilder sb = new StringBuilder("ForStatement: for( ")
		.append(getInitilization().toString()).append(" ; ")
		.append(getCondition().toString()).append(" ; ")
		.append(getIncrement().toString()).append(" )");
	if(!body.isEmpty()){
	    if(body.size()>1){
		sb.append(" {\n");
		for(Statement st : getBody())
		    sb.append("\t").append(st.toString()).append("\n");
		sb.append("}\n");
	    }
	    else{
		for(Statement st : getBody())
		    sb.append("\n").append("\t").append(st.toString());
	    }
	}
	return sb.toString();
    }
}