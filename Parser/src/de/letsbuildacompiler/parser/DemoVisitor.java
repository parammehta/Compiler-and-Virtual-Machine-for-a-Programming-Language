// Generated from Demo.g4 by ANTLR 4.7
package de.letsbuildacompiler.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DemoParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DemoVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DemoParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(DemoParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementPiece}
	 * labeled alternative in {@link DemoParser#programPiece}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementPiece(DemoParser.StatementPieceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MethodPiece}
	 * labeled alternative in {@link DemoParser#programPiece}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodPiece(DemoParser.MethodPieceContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(DemoParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MULTDIV}
	 * labeled alternative in {@link DemoParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMULTDIV(DemoParser.MULTDIVContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link DemoParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(DemoParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link DemoParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(DemoParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PLUSMINUS}
	 * labeled alternative in {@link DemoParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPLUSMINUS(DemoParser.PLUSMINUSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MethodExp}
	 * labeled alternative in {@link DemoParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodExp(DemoParser.MethodExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(DemoParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoParser#varAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarAssignment(DemoParser.VarAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoParser#println}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintln(DemoParser.PrintlnContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoParser#method}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod(DemoParser.MethodContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoParser#statementList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementList(DemoParser.StatementListContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoParser#methodCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCall(DemoParser.MethodCallContext ctx);
}