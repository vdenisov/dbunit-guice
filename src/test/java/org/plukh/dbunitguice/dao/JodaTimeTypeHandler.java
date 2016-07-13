package org.plukh.dbunitguice.dao;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.joda.time.DateTime;

import java.sql.*;

@MappedJdbcTypes({JdbcType.DATE, JdbcType.TIME, JdbcType.TIMESTAMP})
public class JodaTimeTypeHandler extends BaseTypeHandler<DateTime> {
    @Override
    public void setNonNullParameter(PreparedStatement stmt, int i, DateTime dateTime, JdbcType jdbcType) throws SQLException {
        switch (jdbcType) {
            case TIMESTAMP:
                stmt.setTimestamp(i, new Timestamp(dateTime.getMillis()));
                break;
            case TIME:
                stmt.setTime(i, new Time(dateTime.getMillis()));
                break;
            case DATE:
                stmt.setDate(i, new Date(dateTime.getMillis()));
                break;
            default:
                throw new SQLException("Unsupported JdbcType: " + jdbcType);
        }
    }

    @Override
    public DateTime getNullableResult(ResultSet rs, String s) throws SQLException {
        Timestamp ts = rs.getTimestamp(s);
        return ts == null ? null : new DateTime(ts.getTime());
    }

    @Override
    public DateTime getNullableResult(ResultSet rs, int i) throws SQLException {
        Timestamp ts = rs.getTimestamp(i);
        return ts == null ? null : new DateTime(ts.getTime());
    }

    @Override
    public DateTime getNullableResult(CallableStatement stmt, int i) throws SQLException {
        Timestamp ts = stmt.getTimestamp(i);
        return ts == null ? null : new DateTime(ts.getTime());
    }
}
