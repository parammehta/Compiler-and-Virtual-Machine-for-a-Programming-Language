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
	 * Visit a parse tree produced by the {@code PLUS}
	 * labeled alternative in {@link DemoParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPLUS(DemoParser.PLUSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link DemoParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(DemoParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MINUS}
	 * labeled alternative in {@link DemoParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMINUS(DemoParser.MINUSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MULT}
	 * labeled alternative in {@link DemoParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMULT(DemoParser.MULTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DIV}
	 * labeled alternative in {@link DemoParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDIV(DemoParser.DIVContext ctx);
	/**
	 * Visit a parse tree produced by {@link DemoParser#println}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintln(DemoParser.PrintlnContext ctx);
}