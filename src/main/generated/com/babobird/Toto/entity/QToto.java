package com.babobird.Toto.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QToto is a Querydsl query type for Toto
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QToto extends EntityPathBase<Toto> {

    private static final long serialVersionUID = 223701765L;

    public static final QToto toto = new QToto("toto");

    public final StringPath totoNm = createString("totoNm");

    public final NumberPath<Integer> totoNo = createNumber("totoNo", Integer.class);

    public QToto(String variable) {
        super(Toto.class, forVariable(variable));
    }

    public QToto(Path<? extends Toto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QToto(PathMetadata metadata) {
        super(Toto.class, metadata);
    }

}

