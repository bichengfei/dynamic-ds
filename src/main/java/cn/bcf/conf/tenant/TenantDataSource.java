package cn.bcf.conf.tenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;


@Component("tenantDataSource")
public class TenantDataSource implements DataSource {

    @Autowired(required = false)
    TenantDataSourceFactory tenantDataSourceFactory;

    @Override
    public Connection getConnection() throws SQLException {
        return tenantDataSourceFactory.getDs().getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return tenantDataSourceFactory.getDs().getConnection(username, password);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return tenantDataSourceFactory.getDs().getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        tenantDataSourceFactory.getDs().setLogWriter(out);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        tenantDataSourceFactory.getDs().setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return tenantDataSourceFactory.getDs().getLoginTimeout();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return tenantDataSourceFactory.getDs().getParentLogger();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return tenantDataSourceFactory.getDs().unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return tenantDataSourceFactory.getDs().isWrapperFor(iface);
    }
}